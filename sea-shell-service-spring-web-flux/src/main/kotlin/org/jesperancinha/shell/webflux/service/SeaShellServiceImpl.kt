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
import kotlin.math.cos

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
    lowerRepository: ShellLowerRepositoryImpl,
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

    fun allSeaShellsReactiveBlock(): ParallelFlux<SeaShellDto> = Mono
        .fromCallable { allSeaShellsNaifBlock() }
        .flux().flatMap { Flux.fromIterable(it) }
        .parallel(parallelism)
        .runOn(Schedulers.boundedElastic())

    fun allSeaShellsReactiveWithDelay(): Flux<SeaShellDto> = allSeaShells()
        .sequential()
        .delayElements(Duration.ofMillis(delay.toLong()))
        .subscribeOn(Schedulers.boundedElastic())

    fun allSeaShellsReactiveWithForkJoins(): Flux<SeaShellDto> = Flux
        .fromStream(
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
    ): RecursiveTask<Stream<ForkJoinTask<SeaShellCostumeDto>>> = SeaShellCostumesRecursiveTask(
        costumeRepository = costumeRepository,
        topRepository = topRepository,
        lowerRepository = lowerRepository,
        seaShellDto = seaShellDto,
        commonPool = commonPool
    )

    private fun getSeaShellPersonsForkJoinTask(
        commonPool: ForkJoinPool,
        seaShellDto: SeaShellDto
    ): SeaShellPersonsRecursiveTask = SeaShellPersonsRecursiveTask(
        personRepository = personRepository,
        accountRepository = accountRepository,
        costumeRepository = costumeRepository,
        topRepository = topRepository,
        lowerRepository = lowerRepository,
        seaShellDto = seaShellDto,
        commonPool = commonPool
    )
}