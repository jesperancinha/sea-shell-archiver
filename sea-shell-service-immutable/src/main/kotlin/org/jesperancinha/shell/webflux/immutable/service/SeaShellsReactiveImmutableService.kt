package org.jesperancinha.shell.webflux.immutable.service

import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.webflux.immutable.data.*
import org.jesperancinha.shell.webflux.immutable.repository.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

/**
 * Created by jofisaes on 04/08/2021
 */
@Service
class SeaShellsReactiveImmutableService(
    private val shellTopRepository: ShellTopImmutableRepository,
    private val shellLowerRepository: ShellLowerImmutableRepository,
    private val shellAccountImmutableRepository: ShellAccountImmutableRepository,
    private val shellCostumeImmutableRepository: ShellCostumeImmutableRepository,
    private val shellPersonImmutableRepository: ShellPersonImmutableRepository,
    private val shellImmutableRepository: ShellImmutableRepository
) {
    fun allShells(): Flux<SeaShellDto> = shellImmutableRepository.findAllShellIds()
        .flatMap { id: Long -> getSeaShellById(id) }

    fun getSeaShellById(id: Long): Mono<SeaShellDto?> {
        return shellImmutableRepository.findSeaShellById(id)
            .flatMap { shell ->
                Mono.zip(
                    shellPersonImmutableRepository
                        .findPersons(shell.persons.personId)
                        .flatMap { person -> personPublisher(person).subscribeOn(Schedulers.parallel()) }
                        .sequential().collectList().subscribeOn(Schedulers.parallel()),
                    shellCostumeImmutableRepository
                        .findCostumes(shell.costumes.costumeId)
                        .flatMap { costume -> costumePublisher(costume).subscribeOn(Schedulers.parallel()) }
                        .sequential().collectList().subscribeOn(Schedulers.parallel())
                ) { persons, costumes ->
                    SeaShellDto(
                        name = shell.name,
                        scientificName = shell.scientificName,
                        personIds = shell.persons.personId,
                        costumeIds = shell.costumes.costumeId,
                        persons = persons,
                        costumes = costumes,
                    )
                }.subscribeOn(Schedulers.parallel())
            }
    }

    fun getPersonById(id: Long): Mono<SeaShellPersonDto> = shellPersonImmutableRepository.findPersonById(id)
        .flatMap { person -> personPublisher(person).subscribeOn(Schedulers.parallel()) }

    fun getCostumeById(id: Long): Mono<SeaShellCostumeDto> = shellCostumeImmutableRepository.findCostumeById(id)
        .flatMap { costume -> costumePublisher(costume).subscribeOn(Schedulers.parallel()) }

    fun getAccountById(id: String): Mono<SeaShellAccountDto> = shellAccountImmutableRepository.findAccountById(id)
        .mapNotNull { account ->
            if (account != null) {
                SeaShellAccountDto.create(account)
            } else null
        }

    fun getTopById(id: Long): Mono<SeaShellTopDto> = shellTopRepository.findTopById(id)
        .map { top -> SeaShellTopDto.create(top) }

    fun getLowerById(id: Long): Mono<SeaShellLowerDto> = shellLowerRepository.findLowerById(id)
        .map { lower -> SeaShellLowerDto.create(lower) }

    private fun personPublisher(person: Person): Mono<SeaShellPersonDto> = Mono.zip(
        getAccountById(person.accountId).subscribeOn(Schedulers.parallel()),
        getCostumeById(person.costumeId).subscribeOn(Schedulers.parallel())
    ) { account, costume ->
        SeaShellPersonDto(
            name = person.name,
            accountId = person.accountId,
            costumeId = person.costumeId,
            accountDto = account,
            costumeDto = costume
        )
    }

    private fun costumePublisher(costume: Costume): Mono<SeaShellCostumeDto> = Mono.zip(
        getTopById(costume.topId).subscribeOn(Schedulers.parallel()),
        getLowerById(costume.lowerId).subscribeOn(Schedulers.parallel())
    ) { top, lower ->
        SeaShellCostumeDto(
            topId = costume.topId,
            lowerId = costume.lowerId,
            topDto = top,
            lowerDto = lower
        )
    }
}
