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
import java.util.function.Consumer

@Service
class SeaShellReactiveServiceImpl(
    shellRepository: ShellRepositoryImpl,
    shellPersonRepository: ShellPersonRepositoryImpl,
    shellCostumeRepository: ShellCostumeRepositoryImpl,
    shellAccountRepository: ShellAccountRepositoryImpl,
    shellTopRepository: ShellTopRepositoryImpl,
    shellLowerRepository: ShellLowerRepositoryImpl
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
            costumes.forEach(Consumer { costume ->
                seaShellDtoReturn.costumes.add(
                    SeaShellConverter.toShellCostumeDto(costume)
                )
            })
            costumes
        }
        .thenMany(Mono.zip(
            fetchCostumeTopPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
            fetchCostumeLowerPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel())
        ) { _, _ -> seaShellDtoReturn }
        ))

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