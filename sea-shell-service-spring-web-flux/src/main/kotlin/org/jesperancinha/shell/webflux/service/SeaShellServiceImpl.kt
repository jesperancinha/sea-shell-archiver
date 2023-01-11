package org.jesperancinha.shell.webflux.service

import lombok.extern.slf4j.Slf4j
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.data.SeaShellDto
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.*
import org.jesperancinha.shell.webflux.service.fork.SeaShellCostumesRecursiveTask
import org.jesperancinha.shell.webflux.service.fork.SeaShellPersonsRecursiveTask
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
import java.util.stream.Collectors
import java.util.stream.Stream

@Slf4j
@Service
@ConditionalOnBean(
    value = [ShellRepositoryImpl::class, ShellCostumeRepositoryImpl::class, ShellPersonRepositoryImpl::class, ShellAccountRepositoryImpl::class, ShellTopRepositoryImpl::class, ShellLowerRepositoryImpl::class],
    name = ["shellRepositoryImpl", "shellCostumeRepositoryImpl", "shellPersonRepositoryImpl", "shellAccountRepositoryImpl", "shellTopRepositoryImpl", "shellLowerRepositoryImpl"]
)
class SeaShellServiceImpl(
    private val shellRepository: ShellRepositoryImpl,
    costumeRepository: ShellCostumeRepositoryImpl,
    personRepository: ShellPersonRepositoryImpl,
    accountRepository: ShellAccountRepositoryImpl,
    topRepository: ShellTopRepositoryImpl,
    lowerRepository: ShellLowerRepositoryImpl
) : SeaShellConsumerAdapter(
    costumeRepository,
    personRepository,
    accountRepository,
    topRepository,
    lowerRepository
) {
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int? = null

    @Value("\${sea.shell.delay.ms:100}")
    private val delay: Int? = null
    fun getSeaShellById(id: Long?): Mono<SeaShellDto?> {
        return shellRepository.findSeaShellById(id)
            .map { obj: Shell? -> SeaShellConverter.toShellDto() }
            .doOnNext(consumerPersons())
            .doOnNext(consumerCostumes())
    }

    val allSeaShells: ParallelFlux<SeaShellDto?>
        get() = shellRepository
            .findAllSeaShells()
            .map { obj: Shell? -> SeaShellConverter.toShellDto() }
            .doOnNext(consumerPersons())
            .doOnNext(consumerCostumes())
    val allSeaShellsNaifBlock: List<SeaShellDto?>
        get() = shellRepository.findAllSeaShellsBlock()
            .parallelStream()
            .map { obj: Shell? -> SeaShellConverter.toShellDto() }
            .peek { seaShellDto: SeaShellDto? ->
                setMainRootElements(
                    seaShellDto!!
                )
            }
            .collect(Collectors.toList())

    fun getSeaShellNaifBlock(id: Long?): SeaShellDto? {
        val seaShellDto = SeaShellConverter.toShellDto(
            shellRepository
                .findSeaShellBlockById(id)
        )
        setMainRootElements(seaShellDto!!)
        return seaShellDto
    }

    val allSeaShellsReactiveBlock: ParallelFlux<SeaShellDto>
        get() = Mono.fromCallable { allSeaShellsNaifBlock }
            .flux().flatMap { it: List<SeaShellDto?>? -> Flux.fromIterable(it) }
            .parallel(parallelism!!)
            .runOn(Schedulers.boundedElastic())
    val allSeaShellsReactiveWithDelay: Flux<SeaShellDto?>
        get() = allSeaShells
            .sequential()
            .delayElements(Duration.ofMillis(delay!!.toLong()))
            .subscribeOn(Schedulers.boundedElastic())
    val allSeaShellsReactiveWithForkJoins: Flux<SeaShellDto?>
        get() = Flux.fromStream(
            shellRepository.findAllSeaShellsBlock().parallelStream()
                .map { shell: Shell? ->
                    val commonPool = ForkJoinPool(100)
                    val seaShellDto = SeaShellConverter.toShellDto(shell)
                    val personDtoStream = commonPool.invoke(getSeaShellPersonsForkJoinTask(commonPool, seaShellDto))
                    val costumeDtoStream = commonPool.invoke(getSeaShellCostumesForkJoinTask(commonPool, seaShellDto))
                    seaShellDto!!.addPersons(personDtoStream.map { obj: ForkJoinTask<SeaShellPersonDto> -> obj.join() }
                        .collect(Collectors.toList()))
                    seaShellDto.addCostumes(costumeDtoStream.map { obj: ForkJoinTask<SeaShellCostumeDto> -> obj.join() }
                        .collect(Collectors.toList()))
                    seaShellDto
                })

    private fun getSeaShellCostumesForkJoinTask(
        commonPool: ForkJoinPool, seaShellDto: SeaShellDto?
    ): RecursiveTask<Stream<ForkJoinTask<SeaShellCostumeDto>>> {
        return SeaShellCostumesRecursiveTask.builder()
            .costumeRepository(costumeRepository)
            .topRepository(topRepository)
            .lowerRepository(lowerRepository)
            .seaShellDto(seaShellDto)
            .commonPool(commonPool)
            .build()
    }

    private fun getSeaShellPersonsForkJoinTask(
        commonPool: ForkJoinPool,
        seaShellDto: SeaShellDto?
    ): SeaShellPersonsRecursiveTask {
        return SeaShellPersonsRecursiveTask.builder()
            .personRepository(personRepository)
            .accountRepository(accountRepository)
            .costumeRepository(costumeRepository)
            .topRepository(topRepository)
            .lowerRepository(lowerRepository)
            .seaShellDto(seaShellDto)
            .commonPool(commonPool).build()
    }
}