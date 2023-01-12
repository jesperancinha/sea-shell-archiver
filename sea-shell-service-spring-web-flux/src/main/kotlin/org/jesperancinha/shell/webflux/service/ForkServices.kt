package org.jesperancinha.shell.webflux.service

import lombok.extern.slf4j.Slf4j
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.data.SeaShellDto
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask
import java.util.function.Consumer
import java.util.stream.Collectors
import java.util.stream.Stream

@Slf4j
@Service
@ConditionalOnBean(
    value = [ShellRepository::class, ShellCostumeRepository::class, ShellPersonRepository::class, ShellAccountRepository::class, ShellTopRepository::class, ShellLowerRepository::class],
    name = ["shellRepository", "shellCostumeRepository", "shellPersonRepository", "shellAccountRepository", "shellTopRepository", "shellLowerRepository"]
)
class SeaShellService(
    private val shellRepository: ShellRepository,
    costumeRepository: ShellCostumeRepository,
    personRepository: ShellPersonRepository,
    accountRepository: ShellAccountRepository,
    topRepository: ShellTopRepository,
    lowerRepository: ShellLowerRepository,
    @Value("\${sea.shell.parallelism:20}")
    val parallelism: Int,
    @Value("\${sea.shell.delay.ms:100}")
    val delay: Int
) : SeaShellConsumerAdapter(
    costumeRepository,
    personRepository,
    accountRepository,
    topRepository,
    lowerRepository
) {

    fun getSeaShellById(id: Long): Mono<SeaShellDto> {
        return shellRepository.findSeaShellById(id)
            .map { SeaShellConverter.toShellDto(it) }
            .doOnNext(consumerPersons())
            .doOnNext(consumerCostumes())
    }

    fun allSeaShells(): ParallelFlux<SeaShellDto> = shellRepository
        .findAllSeaShells()
        .map { SeaShellConverter.toShellDto(it) }
        .doOnNext(consumerPersons())
        .doOnNext(consumerCostumes())

    fun allSeaShellsNaifBlock(): List<SeaShellDto> = shellRepository.findAllSeaShellsBlock()
        .parallelStream()
        .map { SeaShellConverter.toShellDto(it) }
        .peek { seaShellDto: SeaShellDto? ->
            setMainRootElements(
                seaShellDto!!
            )
        }
        .collect(Collectors.toList())

    fun getSeaShellNaifBlock(id: Long): SeaShellDto? {
        val seaShellDto = SeaShellConverter.toShellDto(
            shellRepository
                .findSeaShellBlockById(id)
        )
        setMainRootElements(seaShellDto)
        return seaShellDto
    }

    fun allSeaShellsReactiveBlock(): ParallelFlux<SeaShellDto> = Mono.fromCallable { allSeaShellsNaifBlock() }
        .flux().flatMap { Flux.fromIterable(it) }
        .parallel(parallelism)
        .runOn(Schedulers.boundedElastic())

    fun allSeaShellsReactiveWithDelay(): Flux<SeaShellDto> = allSeaShells()
        .sequential()
        .delayElements(Duration.ofMillis(delay.toLong()))
        .subscribeOn(Schedulers.boundedElastic())

    fun allSeaShellsReactiveWithForkJoins(): Flux<SeaShellDto> = Flux.fromStream(
        shellRepository.findAllSeaShellsBlock().parallelStream()
            .map { shell ->
                val commonPool = ForkJoinPool(100)
                val seaShellDto = SeaShellConverter.toShellDto(shell)
                val personDtoStream = commonPool.invoke(getSeaShellPersonsForkJoinTask(commonPool, seaShellDto))
                val costumeDtoStream = commonPool.invoke(getSeaShellCostumesForkJoinTask(commonPool, seaShellDto))
                seaShellDto.addPersons(personDtoStream.map { forkJoinTask: ForkJoinTask<SeaShellPersonDto> -> forkJoinTask.join() }
                    .collect(Collectors.toList()))
                seaShellDto.addCostumes(costumeDtoStream.map { forkJoinTask: ForkJoinTask<SeaShellCostumeDto> -> forkJoinTask.join() }
                    .collect(Collectors.toList()))
                seaShellDto
            })

    private fun getSeaShellCostumesForkJoinTask(
        commonPool: ForkJoinPool, seaShellDto: SeaShellDto
    ): RecursiveTask<Stream<ForkJoinTask<SeaShellCostumeDto>>> =
        org.jesperancinha.shell.webflux.service.SeaShellCostumesRecursiveTask(
            costumeRepository = costumeRepository,
            topRepository = topRepository,
            lowerRepository = lowerRepository,
            seaShellDto = seaShellDto,
            commonPool = commonPool
        )

    private fun getSeaShellPersonsForkJoinTask(
        commonPool: ForkJoinPool,
        seaShellDto: SeaShellDto
    ): org.jesperancinha.shell.webflux.service.SeaShellPersonsRecursiveTask =
        org.jesperancinha.shell.webflux.service.SeaShellPersonsRecursiveTask(
            personRepository = personRepository,
            accountRepository = accountRepository,
            costumeRepository = costumeRepository,
            topRepository = topRepository,
            lowerRepository = lowerRepository,
            seaShellDto = seaShellDto,
            commonPool = commonPool
        )
}

@Slf4j
open class SeaShellConsumerAdapter(
    protected val costumeRepository: ShellCostumeRepository,
    protected val personRepository: ShellPersonRepository,
    protected val accountRepository: ShellAccountRepository,
    protected val topRepository: ShellTopRepository,
    protected val lowerRepository: ShellLowerRepository
) {
    protected fun consumerPersons(): Consumer<SeaShellDto> {
        return Consumer { seaShellDto: SeaShellDto ->
            personRepository
                .findPersons(seaShellDto.personIds)
                .map { person -> SeaShellConverter.toShellPersonDto(person) }
                .doOnNext { personDto: SeaShellPersonDto? ->
                    seaShellDto
                        .persons
                        .add(personDto)
                }
                .subscribe { seaShellPersonDto ->
                    seaShellPersonDto.costumeId?.let {
                        costumeRepository.findCostumeById(it)
                            .subscribe { costume ->
                                val newSeaShellPersonDto = seaShellPersonDto.copy(
                                    costumeDto = SeaShellConverter.toShellCostumeDto(costume)
                                )
                                val costumeDto = newSeaShellPersonDto.costumeDto

                                if (costumeDto != null) {
                                    consumerCostume().accept(costumeDto)
                                    logger.info("Complete costume before calling sub top/lower threads ->$costumeDto")
                                }
                            }
                    }
                    seaShellPersonDto.accountId?.let {
                        accountRepository.findAccountById(it)
                            .subscribe { account ->
                                seaShellPersonDto.copy(accountDto = SeaShellConverter.toAccountDto(account))
                                    .also {
                                        logger.info("Complete account after calling account thread ->" + seaShellPersonDto.accountDto)
                                    }
                            }
                    }
                }
        }
    }

    protected fun consumerCostumes(): Consumer<SeaShellDto> {
        return Consumer { seaShellDto: SeaShellDto ->
            costumeRepository
                .findCostumes(seaShellDto.costumeIds)
                .map { costume -> SeaShellConverter.toShellCostumeDto(costume) }
                .doOnNext { seaShellCostumeDto: SeaShellCostumeDto? ->
                    seaShellDto
                        .costumes
                        .add(seaShellCostumeDto)
                }
                .subscribe(consumerCostume())
        }
    }

    private fun consumerCostume(): Consumer<SeaShellCostumeDto> {
        return Consumer { seaShellCostumeDto ->
            seaShellCostumeDto.topId
                ?.let {
                    topRepository.findTopById(it)
                        .subscribe { top ->
                            seaShellCostumeDto.copy(
                                topDto = SeaShellConverter.toTopDto(top)
                            )
                                .also {
                                    logger.info("Complete costume after calling sub top thread ->$it")
                                }
                        }
                }
            seaShellCostumeDto.lowerId
                ?.let {
                    lowerRepository.findLowerById(it)
                        .subscribe { lower ->
                            seaShellCostumeDto.copy(
                                lowerDto = SeaShellConverter.toLowerDto(lower)
                            )
                                .also {
                                    logger.info("Complete costume after calling sub top thread ->$seaShellCostumeDto")
                                }
                        }
                }
        }
    }

    protected fun setMainRootElements(seaShellDto: SeaShellDto) {
        seaShellDto.addCostumes(
            costumeRepository
                .findCostumesBlock(seaShellDto.costumeIds)
                .parallelStream()
                .map { costume ->
                    SeaShellConverter.toShellCostumeDto(costume)
                        .setCostumeRootElements()
                }
                .collect(Collectors.toList()))
        seaShellDto.addPersons(
            personRepository
                .findPersonsBlock(seaShellDto.personIds)
                .map { person ->
                    SeaShellConverter.toShellPersonDto(person)
                        .let {
                            it.copy(
                                accountDto = it.accountId
                                    ?.let { accountId -> accountRepository.findAccountByIdBlock(accountId) }
                                    ?.let { account -> SeaShellConverter.toAccountDto(account) })
                        }
                        .let { seaShellPersonDto ->
                            seaShellPersonDto
                                .copy(
                                    costumeDto =
                                    seaShellPersonDto.costumeId
                                        ?.let { costumeRepository.findCostumeByIdBlock(it) }
                                        ?.let {
                                            SeaShellConverter.toShellCostumeDto(it)
                                        })
                        }
                        .let { seaShellPersonDto ->
                            seaShellPersonDto.costumeDto.let {
                                if (it != null) {
                                    seaShellPersonDto.setCostumeRootElements(it)
                                } else null
                            }
                        }
                })
    }

    private fun SeaShellPersonDto.setCostumeRootElements(seaShellCostumeDto: SeaShellCostumeDto) =
        seaShellCostumeDto.setCostumeRootElements()
            .let { costumeDto -> this.copy(costumeDto = costumeDto) }

    private fun SeaShellCostumeDto.setCostumeRootElements() =
        this.copy(
            topDto = this.topId?.let { topRepository.findTopByIdBlock(it) }?.let { SeaShellConverter.toTopDto(it) },
            lowerDto = this.lowerId?.let { SeaShellConverter.toLowerDto(lowerRepository.findLowerByIdBlock(this.lowerId)) }
        )

    companion object {
        val logger: Logger = LoggerFactory.getLogger(SeaShellConsumerAdapter::class.java)
    }
}