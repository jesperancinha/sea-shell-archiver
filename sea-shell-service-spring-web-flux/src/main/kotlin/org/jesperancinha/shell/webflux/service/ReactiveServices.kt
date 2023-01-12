package org.jesperancinha.shell.webflux.service

import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.webflux.data.*
import org.jesperancinha.shell.webflux.repo.*
import org.springframework.data.util.Pair
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers

open class SeaShellOneAdapter(
    protected val shellRepository: ShellRepository,
    protected val shellPersonRepository: ShellPersonRepository,
    protected val shellCostumeRepository: ShellCostumeRepository,
    protected val shellAccountRepository: ShellAccountRepository,
    protected val shellTopRepository: ShellTopRepository,
    protected val shellLowerRepository: ShellLowerRepository
)

@Service
class SeaShellReactiveService(
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
            shellPersonRepository.findPersonById(idPerson).map { it.toShellPersonDto() }
                .subscribeOn(Schedulers.parallel()),
            shellCostumeRepository.findCostumeById(idCostume)
                .map { it.toShellCostumeDto() }
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
            shellTopRepository.findTopById(idTop).map { it.toTopDto() }
                .subscribeOn(Schedulers.parallel()),
            shellLowerRepository.findLowerById(idLower).map { it.toLowerDto() }
                .subscribeOn(Schedulers.parallel())) { topDto, lowerDto ->
            Pair.of(
                topDto,
                lowerDto
            )
        }
            .subscribeOn(Schedulers.parallel())
    }

    private fun fetchSeaShellPublisher(seaShell: Shell): Mono<SeaShellDto?> {
        val seaShellDtoReturn = seaShell.toShellDto()
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
                        person.toShellPersonDto()
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
                    costume.toShellCostumeDto()
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
                    ?.toLowerDto()
                )
            }.let { seaShellDtoReturn.copy(costumes = it.toMutableList()) }
    }


    private fun fetchCostumeTopPublisher(seaShellDtoReturn: SeaShellDto) = Mono.fromCallable {
        seaShellDtoReturn.costumes
            .map { seaShellCostumeDto ->
                seaShellCostumeDto?.copy(topDto = seaShellCostumeDto.topId
                    ?.let { shellTopRepository.findTopByIdBlock(it) }
                    ?.toTopDto()
                )
            }.let { seaShellDtoReturn.copy(costumes = it.toMutableList()) }
    }

    private fun fetchPersonAccountPublisher(seaShellDtoReturn: SeaShellDto) = Mono.fromCallable {
        seaShellDtoReturn.persons
            .map { seaShellPersonDto ->
                seaShellPersonDto?.copy(
                    accountDto = seaShellPersonDto.accountId
                        ?.let { shellAccountRepository.findAccountByIdBlock(it) }
                        ?.toAccountDto()
                )
            }.let { seaShellDtoReturn.copy(persons = it.toMutableList()) }
    }

    private fun fetchPersonCostumePublisher(seaShellDtoReturn: SeaShellDto): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn.persons
                .map { seaShellPersonDto: SeaShellPersonDto? ->
                    seaShellPersonDto?.copy(
                        costumeDto = seaShellPersonDto.costumeId
                            ?.let { shellCostumeRepository.findCostumeByIdBlock(it) }
                            ?.toShellCostumeDto()
                    )
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
                                ?.toLowerDto()
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
                    seaShellPersonDto?.copy(
                        costumeDto = costumeDto?.copy(
                            topDto = costumeDto.topId
                                ?.let { shellTopRepository.findTopByIdBlock(it) }
                                ?.toTopDto()
                        )
                    )
                }.let { seaShellDtoReturn.copy(persons = it.toMutableList()) }
        }
    }
}

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
            .map { it.toShellDto() }
    }

    fun getPersonById(id: Long): Mono<SeaShellPersonDto> {
        return shellPersonRepository.findPersonById(id)
            .map { it.toShellPersonDto() }
    }

    fun getCostumeById(id: Long): Mono<SeaShellCostumeDto> {
        return shellCostumeRepository.findCostumeById(id)
            .map { it.toShellCostumeDto() }
    }

    fun getAccountById(id: String): Mono<SeaShellAccountDto> {
        return shellAccountRepository.findAccountById(id)
            .map { it.toAccountDto() }
    }

    fun getTopById(id: Long): Mono<SeaShellTopDto> {
        return shellTopRepository.findTopById(id)
            .map { it.toTopDto() }
    }

    fun getLowerById(id: Long): Mono<SeaShellLowerDto> {
        return shellLowerRepository.findLowerById(id)
            .map { it.toLowerDto() }
    }
}