package org.jesperancinha.shell.webflux.service

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.webflux.data.*
import org.jesperancinha.shell.webflux.repo.*
import org.springframework.data.util.Pair
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask
import java.util.stream.Stream

@AllArgsConstructor
@Builder
class SeaShellCostumeRecursiveTask(
    private val costumeRepository: ShellCostumeRepository,
    private val topRepository: ShellTopRepository,
    private val lowerRepository: ShellLowerRepository,
    private val seaShellPersonDto: SeaShellPersonDto,
    private val commonPool: ForkJoinPool
) : org.jesperancinha.shell.webflux.service.SeaShelTopLowerAdapter<SeaShellPersonDto>() {

    override fun compute(): SeaShellPersonDto = seaShellPersonDto.copy(
        costumeDto = seaShellPersonDto.costumeId
            ?.let { costumeRepository.findCostumeByIdBlock(it) }
            ?.let {
                SeaShellConverter.toShellCostumeDto(
                    it
                )
            }
    ).also { personDto ->
        val costumeDto = personDto.costumeDto
        val forkTopJoinTask = costumeDto?.let { getSeaShellCostumeTopForkJoinTask(topRepository, it, commonPool) }
        val forkLowerJoinTask = costumeDto?.let { getSeaShellCostumeLowerForkJoinTask(lowerRepository, it, commonPool) }
        forkTopJoinTask?.join()
        forkLowerJoinTask?.join()
    }
}

@Builder
@AllArgsConstructor
class SeaShellCostumesRecursiveTask(
    private val costumeRepository: ShellCostumeRepository,
    private val topRepository: ShellTopRepository,
    private val lowerRepository: ShellLowerRepository,
    private val seaShellDto: SeaShellDto,
    private val commonPool: ForkJoinPool
) : org.jesperancinha.shell.webflux.service.SeaShelTopLowerAdapter<Stream<ForkJoinTask<SeaShellCostumeDto>>>() {

    override fun compute(): Stream<ForkJoinTask<SeaShellCostumeDto>> {
        val costumesBlock = costumeRepository.findCostumesBlock(seaShellDto.costumeIds)
        return costumesBlock.map { costume -> SeaShellConverter.toShellCostumeDto(costume) }
            .stream().flatMap { seaShellCostumeDto ->
                Stream.of(
                    getSeaShellCostumeTopForkJoinTask(
                        topRepository = topRepository, costumeDto = seaShellCostumeDto, commonPool = commonPool
                    ),
                    getSeaShellCostumeLowerForkJoinTask(
                        lowerRepository = lowerRepository, costumeDto = seaShellCostumeDto, commonPool = commonPool
                    )
                )
            }
    }
}

@Builder
@AllArgsConstructor
class SeaShellLowerRecursiveTask(
    private val lowerRepository: ShellLowerRepository,
    private val costumeDto: SeaShellCostumeDto
) : RecursiveTask<SeaShellCostumeDto>() {

    override fun compute(): SeaShellCostumeDto =
        costumeDto.copy(lowerDto = costumeDto.lowerId
            ?.let { lowerRepository.findLowerByIdBlock(it) }
            ?.let { SeaShellConverter.toLowerDto(it) })
}

@AllArgsConstructor
@Builder
class SeaShellPersonAccountRecursiveTask(
    private val accountRepository: ShellAccountRepository,
    private val seaShellPersonDto: SeaShellPersonDto
) : RecursiveTask<SeaShellPersonDto>() {

    override fun compute(): SeaShellPersonDto = seaShellPersonDto.copy(
        accountDto = seaShellPersonDto.accountId
            ?.let { accountRepository.findAccountByIdBlock(it) }
            ?.let {
                SeaShellConverter.toAccountDto(it)
            }
    )
}

@AllArgsConstructor
@Builder
class SeaShellPersonsRecursiveTask(
    private val personRepository: ShellPersonRepository,
    private val accountRepository: ShellAccountRepository,
    private val costumeRepository: ShellCostumeRepository,
    private val topRepository: ShellTopRepository,
    private val lowerRepository: ShellLowerRepository,
    private val seaShellDto: SeaShellDto,
    private val commonPool: ForkJoinPool
) : RecursiveTask<Stream<ForkJoinTask<SeaShellPersonDto>>>() {

    override fun compute(): Stream<ForkJoinTask<SeaShellPersonDto>>? =
        personRepository.findPersonsBlock(seaShellDto.personIds)
            .map { person -> SeaShellConverter.toShellPersonDto(person) }
            .stream().flatMap { seaShellPersonDto ->
                getSeaShellPersonForkJoinTaskStream(
                    seaShellPersonDto,
                    commonPool
                )
            }

    private fun getSeaShellPersonForkJoinTaskStream(
        seaShellPersonDto: SeaShellPersonDto, commonPool: ForkJoinPool
    ): Stream<ForkJoinTask<SeaShellPersonDto>> {
        return Stream.of(
            commonPool.submit(
                org.jesperancinha.shell.webflux.service.SeaShellPersonAccountRecursiveTask(
                    accountRepository = accountRepository,
                    seaShellPersonDto = seaShellPersonDto
                )
            ),
            commonPool.submit(
                org.jesperancinha.shell.webflux.service.SeaShellCostumeRecursiveTask(
                    costumeRepository = costumeRepository,
                    topRepository = topRepository,
                    lowerRepository = lowerRepository,
                    seaShellPersonDto = seaShellPersonDto,
                    commonPool = commonPool
                )
            )
        )
    }
}

@Builder
@AllArgsConstructor
class SeaShellTopRecursiveTask(
    val topRepository: ShellTopRepository,
    val costumeDto: SeaShellCostumeDto
) : RecursiveTask<SeaShellCostumeDto>() {

    override fun compute(): SeaShellCostumeDto =
        costumeDto.copy(topDto = costumeDto.topId?.let {
            topRepository.findTopByIdBlock(it)
        }?.let { SeaShellConverter.toTopDto(it) })
}

abstract class SeaShelTopLowerAdapter<T> : RecursiveTask<T>() {
    fun getSeaShellCostumeLowerForkJoinTask(
        lowerRepository: ShellLowerRepository, costumeDto: SeaShellCostumeDto, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> = commonPool.submit(
        org.jesperancinha.shell.webflux.service.SeaShellLowerRecursiveTask(
            lowerRepository = lowerRepository,
            costumeDto = costumeDto
        )
    )

    fun getSeaShellCostumeTopForkJoinTask(
        topRepository: ShellTopRepository, costumeDto: SeaShellCostumeDto, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> = commonPool.submit(
        org.jesperancinha.shell.webflux.service.SeaShellTopRecursiveTask(
            topRepository = topRepository,
            costumeDto = costumeDto
        )
    )
}

open class SeaShellOneAdapter(
    protected val shellRepository: ShellRepository,
    protected val shellPersonRepository: ShellPersonRepository,
    protected val shellCostumeRepository: ShellCostumeRepository,
    protected val shellAccountRepository: ShellAccountRepository,
    protected val shellTopRepository: ShellTopRepository,
    protected val shellLowerRepository: ShellLowerRepository
)

@Service
class SeaShellReactiveServiceImpl(
    shellRepository: ShellRepository,
    shellPersonRepository: ShellPersonRepository,
    shellCostumeRepository: ShellCostumeRepository,
    shellAccountRepository: ShellAccountRepository,
    shellTopRepository: ShellTopRepository,
    shellLowerRepository: ShellLowerRepository
) : SeaShellOneAdapter(
    shellRepository,
    shellPersonRepository,
    shellCostumeRepository,
    shellAccountRepository,
    shellTopRepository,
    shellLowerRepository
) {
    fun allSeaShells(): ParallelFlux<SeaShellDto> = shellRepository
        .findAllSeaShells()
        .map { seaShell -> fetchSeaShellPublisher(seaShell) }
        .flatMap { source -> Flux.from(source) }

    fun getShell(id: Long): Mono<SeaShellDto> {
        return shellRepository.findSeaShellById(id)
            .map { seaShell -> fetchSeaShellPublisher(seaShell) }
            .flatMap { source -> Mono.from(source) }
    }

    fun getRootShell(idPerson: Long?, idCostume: Long): Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> {
        return Mono.zip<SeaShellPersonDto?, SeaShellCostumeDto?, Pair<SeaShellPersonDto, SeaShellCostumeDto>>(
            shellPersonRepository.findPersonById(idPerson).map { SeaShellConverter.toShellPersonDto(it) }
                .subscribeOn(Schedulers.parallel()),
            shellCostumeRepository.findCostumeById(idCostume)
                .map { SeaShellConverter.toShellCostumeDto(it) }
                .subscribeOn(Schedulers.parallel())) { personDto, costumeDto ->
            Pair.of(
                personDto,
                costumeDto
            )
        }
            .subscribeOn(Schedulers.parallel())
    }

    fun getRootCostume(idTop: Long, idLower: Long): Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> {
        return Mono.zip<SeaShellTopDto?, SeaShellLowerDto?, Pair<SeaShellTopDto, SeaShellLowerDto>>(
            shellTopRepository.findTopById(idTop).map { SeaShellConverter.toTopDto(it) }
                .subscribeOn(Schedulers.parallel()),
            shellLowerRepository.findLowerById(idLower).map { SeaShellConverter.toLowerDto(it) }
                .subscribeOn(Schedulers.parallel())) { topDto, lowerDto ->
            Pair.of(
                topDto,
                lowerDto
            )
        }
            .subscribeOn(Schedulers.parallel())
    }

    private fun fetchSeaShellPublisher(seaShell: Shell): Mono<SeaShellDto?> {
        val seaShellDtoReturn = SeaShellConverter.toShellDto(seaShell)
        return Mono.zip(
            fetchPersonsPublisher(seaShell, seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
            fetchCostumesPublisher(seaShell, seaShellDtoReturn).subscribeOn(Schedulers.parallel())
        ) { _, _ -> seaShellDtoReturn }
    }

    private fun fetchPersonsPublisher(seaShell: Shell, seaShellDtoReturn: SeaShellDto): Mono<*> {
        return Mono.from(Mono.from(Mono.just(
            seaShell.persons.personId
        ).subscribeOn(Schedulers.parallel())
            .map { personIds: List<Long> -> shellPersonRepository.findPersonsBlock(personIds) }
            .subscribeOn(Schedulers.parallel())
            .map { persons ->
                persons.forEach { person ->
                    seaShellDtoReturn.persons.add(
                        SeaShellConverter.toShellPersonDto(person)
                    )
                }
                seaShellDtoReturn.persons
            })
            .thenMany(
                Mono.zip(
                    fetchPersonAccountPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
                    fetchPersonFullCostumePublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel())
                )
            )
        )
    }

    private fun fetchPersonFullCostumePublisher(seaShellDtoReturn: SeaShellDto): Mono<SeaShellDto?> {
        return Mono.from(
            fetchPersonCostumePublisher(seaShellDtoReturn)
                .thenMany(fetchPersonTopLowerPublisher(seaShellDtoReturn))
        )
    }

    private fun fetchPersonTopLowerPublisher(seaShellDtoReturn: SeaShellDto): Mono<SeaShellDto?> {
        return Mono.zip(
            fetchPersonCostumeTopPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
            fetchPersonCostumeLowerPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel())
        ) { _, _ -> seaShellDtoReturn }
    }

    private fun fetchCostumesPublisher(seaShell: Shell, seaShellDtoReturn: SeaShellDto) = Mono.from(Mono.just(
        seaShell.costumes.costumeId
    ).subscribeOn(Schedulers.parallel())
        .map { costumeIds: List<Long> -> shellCostumeRepository.findCostumesBlock(costumeIds) }
        .subscribeOn(Schedulers.parallel())
        .map { costumes ->
            costumes.forEach { costume ->
                seaShellDtoReturn.costumes.add(
                    SeaShellConverter.toShellCostumeDto(costume)
                )
            }
            costumes
        }
        .thenMany(Mono.zip(
            fetchCostumeTopPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
            fetchCostumeLowerPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel())
        ) { _, _ -> seaShellDtoReturn })
    )

    private fun fetchCostumeLowerPublisher(seaShellDtoReturn: SeaShellDto) = Mono.fromCallable {
        seaShellDtoReturn.costumes
            .map { seaShellCostumeDto ->
                seaShellCostumeDto?.copy(lowerDto = seaShellCostumeDto.lowerId
                    ?.let { shellLowerRepository.findLowerByIdBlock(it) }
                    ?.let { SeaShellConverter.toLowerDto(it) })
            }.let { seaShellDtoReturn.copy(costumes = it.toMutableList()) }
    }


    private fun fetchCostumeTopPublisher(seaShellDtoReturn: SeaShellDto) = Mono.fromCallable {
        seaShellDtoReturn.costumes
            .map { seaShellCostumeDto ->
                seaShellCostumeDto?.copy(topDto = seaShellCostumeDto.topId
                    ?.let { shellTopRepository.findTopByIdBlock(it) }
                    ?.let { SeaShellConverter.toTopDto(it) })
            }.let { seaShellDtoReturn.copy(costumes = it.toMutableList()) }
    }

    private fun fetchPersonAccountPublisher(seaShellDtoReturn: SeaShellDto) = Mono.fromCallable {
        seaShellDtoReturn.persons
            .map { seaShellPersonDto ->
                seaShellPersonDto?.copy(
                    accountDto = seaShellPersonDto.accountId
                        ?.let { shellAccountRepository.findAccountByIdBlock(it) }
                        ?.let { SeaShellConverter.toAccountDto(it) }
                )
            }.let { seaShellDtoReturn.copy(persons = it.toMutableList()) }
    }

    private fun fetchPersonCostumePublisher(seaShellDtoReturn: SeaShellDto): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn.persons
                .map { seaShellPersonDto: SeaShellPersonDto? ->
                    seaShellPersonDto?.copy(costumeDto = seaShellPersonDto.costumeId
                        ?.let { shellCostumeRepository.findCostumeByIdBlock(it) }
                        ?.let { SeaShellConverter.toShellCostumeDto(it) })
                }.let { seaShellDtoReturn.copy(persons = it.toMutableList()) }

        }
    }

    private fun fetchPersonCostumeLowerPublisher(seaShellDtoReturn: SeaShellDto): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn.persons
                .map { seaShellPersonDto ->
                    val costumeDto = seaShellPersonDto?.costumeDto
                    seaShellPersonDto?.copy(
                        costumeDto = costumeDto?.copy(
                            lowerDto = costumeDto.topId
                                ?.let { shellLowerRepository.findLowerByIdBlock(it) }
                                ?.let {
                                    SeaShellConverter.toLowerDto(it)
                                }
                        )
                    )
                }.let { seaShellDtoReturn.copy(persons = it.toMutableList()) }
        }
    }

    private fun fetchPersonCostumeTopPublisher(seaShellDtoReturn: SeaShellDto): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn.persons
                .map { seaShellPersonDto ->
                    val costumeDto = seaShellPersonDto?.costumeDto
                    seaShellPersonDto?.copy(costumeDto = costumeDto?.copy(topDto = costumeDto.topId
                        ?.let { shellTopRepository.findTopByIdBlock(it) }
                        ?.let {
                            SeaShellConverter.toTopDto(
                                it
                            )
                        })
                    )
                }.let { seaShellDtoReturn.copy(persons = it.toMutableList()) }
        }
    }
}

@Slf4j
@Service
class SeaShellReactiveOneServiceImpl(
    shellRepository: ShellRepository,
    shellPersonRepository: ShellPersonRepository,
    shellCostumeRepository: ShellCostumeRepository,
    shellAccountRepository: ShellAccountRepository,
    shellTopRepository: ShellTopRepository,
    shellLowerRepository: ShellLowerRepository
) : SeaShellOneAdapter(
    shellRepository,
    shellPersonRepository,
    shellCostumeRepository,
    shellAccountRepository,
    shellTopRepository,
    shellLowerRepository
) {
    fun allIds(): Flux<Long> = shellRepository.findAllShellIds()

    fun getSeaShellById(id: Long): Mono<SeaShellDto> {
        return shellRepository.findSeaShellById(id)
            .map { SeaShellConverter.toShellDto(it) }
    }

    fun getPersonById(id: Long): Mono<SeaShellPersonDto> {
        return shellPersonRepository.findPersonById(id)
            .map { SeaShellConverter.toShellPersonDto(it) }
    }

    fun getCostumeById(id: Long): Mono<SeaShellCostumeDto> {
        return shellCostumeRepository.findCostumeById(id)
            .map { SeaShellConverter.toShellCostumeDto(it) }
    }

    fun getAccountById(id: String): Mono<SeaShellAccountDto> {
        return shellAccountRepository.findAccountById(id)
            .map { SeaShellConverter.toAccountDto(it) }
    }

    fun getTopById(id: Long): Mono<SeaShellTopDto> {
        return shellTopRepository.findTopById(id)
            .map { SeaShellConverter.toTopDto(it) }
    }

    fun getLowerById(id: Long): Mono<SeaShellLowerDto> {
        return shellLowerRepository.findLowerById(id)
            .map { SeaShellConverter.toLowerDto(it) }
    }
}