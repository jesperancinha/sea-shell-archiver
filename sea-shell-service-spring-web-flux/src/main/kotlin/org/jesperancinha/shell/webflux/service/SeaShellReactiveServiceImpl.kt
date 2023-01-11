package org.jesperancinha.shell.webflux.service

import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.tops.Top
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
    val allSeaShells: ParallelFlux<SeaShellDto>
        get() = shellRepository
            .findAllSeaShells()
            .map { seaShell: Shell? -> fetchSeaShellPublisher(seaShell) }
            .flatMap { source: Mono<SeaShellDto?>? -> Flux.from(source) }

    fun getShell(id: Long?): Mono<SeaShellDto> {
        return shellRepository.findSeaShellById(id).map { seaShell: Shell? -> fetchSeaShellPublisher(seaShell) }
            .flatMap { source: Mono<SeaShellDto?>? -> Mono.from(source) }
    }

    fun getRootShell(idPerson: Long?, idCostume: Long?): Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> {
        return Mono.zip<SeaShellPersonDto?, SeaShellCostumeDto?, Pair<SeaShellPersonDto, SeaShellCostumeDto>>(
            shellPersonRepository.findPersonById(idPerson).map { obj: Person? -> SeaShellConverter.toShellPersonDto() }
                .subscribeOn(Schedulers.parallel()),
            shellCostumeRepository.findCostumeById(idCostume)
                .map { obj: Costume? -> SeaShellConverter.toShellCostumeDto() }
                .subscribeOn(Schedulers.parallel())) { first: SeaShellPersonDto?, second: SeaShellCostumeDto? ->
            Pair.of(
                first,
                second
            )
        }
            .subscribeOn(Schedulers.parallel())
    }

    fun getRootCostume(idTop: Long?, idLower: Long?): Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> {
        return Mono.zip<SeaShellTopDto?, SeaShellLowerDto?, Pair<SeaShellTopDto, SeaShellLowerDto>>(
            shellTopRepository.findTopById(idTop).map { obj: Top? -> SeaShellConverter.toTopDto() }
                .subscribeOn(Schedulers.parallel()),
            shellLowerRepository.findLowerById(idLower).map { obj: Lower? -> SeaShellConverter.toLowerDto() }
                .subscribeOn(Schedulers.parallel())) { first: SeaShellTopDto?, second: SeaShellLowerDto? ->
            Pair.of(
                first,
                second
            )
        }
            .subscribeOn(Schedulers.parallel())
    }

    private fun fetchSeaShellPublisher(seaShell: Shell?): Mono<SeaShellDto?> {
        val seaShellDtoReturn = SeaShellConverter.toShellDto(seaShell)
        return Mono.zip(
            fetchPersonsPublisher(seaShell, seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
            fetchCostumesPublisher(seaShell, seaShellDtoReturn).subscribeOn(Schedulers.parallel())
        ) { persons: Any?, costumes: Any? -> seaShellDtoReturn }
    }

    private fun fetchPersonsPublisher(seaShell: Shell?, seaShellDtoReturn: SeaShellDto?): Mono<*> {
        return Mono.from(Mono.from(Mono.just(
            seaShell!!.persons.personId
        ).subscribeOn(Schedulers.parallel())
            .map { personIds: List<Long> -> shellPersonRepository.findPersonsBlock(personIds) }
            .subscribeOn(Schedulers.parallel())
            .map { persons: List<Person?>? ->
                persons!!.forEach(Consumer { person: Person? ->
                    seaShellDtoReturn!!.persons.add(
                        SeaShellConverter.toShellPersonDto(person)
                    )
                })
                seaShellDtoReturn!!.persons
            })
            .thenMany(
                Mono.zip(
                    fetchPersonAccountPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
                    fetchPersonFullCostumePublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel())
                )
            )
        )
    }

    private fun fetchPersonFullCostumePublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.from(
            fetchPersonCostumePublisher(seaShellDtoReturn)
                .thenMany(fetchPersonTopLowerPublisher(seaShellDtoReturn))
        )
    }

    private fun fetchPersonTopLowerPublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.zip(
            fetchPersonCostumeTopPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
            fetchPersonCostumeLowerPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel())
        ) { persons: SeaShellDto?, costumes: SeaShellDto? -> seaShellDtoReturn }
    }

    private fun fetchCostumesPublisher(seaShell: Shell?, seaShellDtoReturn: SeaShellDto?): Mono<*> {
        return Mono.from(Mono.just(
            seaShell!!.costumes.costumeId
        ).subscribeOn(Schedulers.parallel())
            .map { costumeIds: List<Long> -> shellCostumeRepository.findCostumesBlock(costumeIds) }
            .subscribeOn(Schedulers.parallel())
            .map { costumes: List<Costume?>? ->
                costumes!!.forEach(Consumer { costume: Costume? ->
                    seaShellDtoReturn!!.costumes.add(
                        SeaShellConverter.toShellCostumeDto(costume)
                    )
                })
                costumes
            }
            .thenMany(Mono.zip(
                fetchCostumeTopPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
                fetchCostumeLowerPublisher(seaShellDtoReturn).subscribeOn(Schedulers.parallel())
            ) { persons: SeaShellDto?, costumes: SeaShellDto? -> seaShellDtoReturn }
            ))
    }

    private fun fetchCostumeLowerPublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn!!.costumes.parallelStream().map { seaShellCostumeDto: SeaShellCostumeDto? ->
                seaShellCostumeDto.setLowerDto(
                    SeaShellConverter.toLowerDto(
                        shellLowerRepository.findLowerByIdBlock(seaShellCostumeDto.getLowerId())
                    )
                )
                seaShellDtoReturn
            }.findFirst().orElse(seaShellDtoReturn)
        }
    }

    private fun fetchCostumeTopPublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn!!.costumes.parallelStream().map { seaShellCostumeDto: SeaShellCostumeDto? ->
                seaShellCostumeDto.setTopDto(
                    SeaShellConverter.toTopDto(
                        shellTopRepository.findTopByIdBlock(seaShellCostumeDto.getTopId())
                    )
                )
                seaShellDtoReturn
            }.findFirst().orElse(seaShellDtoReturn)
        }
    }

    private fun fetchPersonAccountPublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn!!.persons
                .forEach(Consumer { seaShellPersonDto: SeaShellPersonDto? ->
                    seaShellPersonDto
                        .setAccountDto(
                            SeaShellConverter.toAccountDto(
                                shellAccountRepository
                                    .findAccountByIdBlock(seaShellPersonDto.getAccountId())
                            )
                        )
                })
            seaShellDtoReturn
        }
    }

    private fun fetchPersonCostumePublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn!!.persons
                .forEach(Consumer { seaShellPersonDto: SeaShellPersonDto? ->
                    seaShellPersonDto
                        .setCostumeDto(
                            SeaShellConverter.toShellCostumeDto(
                                shellCostumeRepository
                                    .findCostumeByIdBlock(seaShellPersonDto.getCostumeId())
                            )
                        )
                })
            seaShellDtoReturn
        }
    }

    private fun fetchPersonCostumeLowerPublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn!!.persons.parallelStream().map { seaShellPersonDto: SeaShellPersonDto? ->
                val costumeDto = seaShellPersonDto.getCostumeDto()
                costumeDto.lowerDto = SeaShellConverter.toLowerDto(
                    shellLowerRepository.findLowerByIdBlock(costumeDto.topId)
                )
                seaShellDtoReturn
            }.findFirst().orElse(seaShellDtoReturn)
        }
    }

    private fun fetchPersonCostumeTopPublisher(seaShellDtoReturn: SeaShellDto?): Mono<SeaShellDto?> {
        return Mono.fromCallable {
            seaShellDtoReturn!!.persons.parallelStream().map { seaShellPersonDto: SeaShellPersonDto? ->
                val costumeDto = seaShellPersonDto.getCostumeDto()
                costumeDto.topDto = SeaShellConverter.toTopDto(
                    shellTopRepository.findTopByIdBlock(costumeDto.topId)
                )
                seaShellDtoReturn
            }.findFirst().orElse(seaShellDtoReturn)
        }
    }
}