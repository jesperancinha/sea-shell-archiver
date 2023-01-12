package org.jesperancinha.shell.webflux.immutable.service

import org.jesperancinha.shell.client.accounts.Account
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
import java.util.function.Function

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
    val allShells: Flux<SeaShellDto?>
        get() = shellImmutableRepository.findAllShellIds()
            .flatMap { id: Long? -> getSeaShellById(id) }

    fun getSeaShellById(id: Long?): Mono<SeaShellDto?> {
        return shellImmutableRepository.findSeaShellById(id)
            .flatMap { shell: Shell? ->
                Mono.zip(
                    shellPersonImmutableRepository
                        .findPersons(shell!!.persons.personId)
                        .flatMap { person: Person? -> personPublisher(person).subscribeOn(Schedulers.parallel()) }
                        .sequential().collectList().subscribeOn(Schedulers.parallel()),
                    shellCostumeImmutableRepository
                        .findCostumes(shell.costumes.costumeId)
                        .flatMap { costume: Costume? -> costumePublisher(costume).subscribeOn(Schedulers.parallel()) }
                        .sequential().collectList().subscribeOn(Schedulers.parallel())
                ) { persons: List<SeaShellPersonDto>?, costumes: List<SeaShellCostumeDto>? ->
                    SeaShellDto.builder()
                        .name(shell.name)
                        .scientificName(shell.scientificName)
                        .personIds(shell.persons.personId)
                        .costumeIds(shell.costumes.costumeId)
                        .persons(persons)
                        .costumes(costumes)
                        .build()
                }.subscribeOn(Schedulers.parallel())
            }
    }

    fun getPersonById(id: Long?): Mono<SeaShellPersonDto> {
        return shellPersonImmutableRepository.findPersonById(id)
            .flatMap { person: Person? -> personPublisher(person).subscribeOn(Schedulers.parallel()) }
    }

    fun getCostumeById(id: Long?): Mono<SeaShellCostumeDto> {
        return shellCostumeImmutableRepository.findCostumeById(id)
            .flatMap { costume: Costume? -> costumePublisher(costume).subscribeOn(Schedulers.parallel()) }
    }

    fun getAccountById(id: String?): Mono<SeaShellAccountDto> {
        return shellAccountImmutableRepository.findAccountById(id)
            .map(Function { account: Account? -> SeaShellAccountDto.Companion.create(account) })
    }

    fun getTopById(id: Long?): Mono<SeaShellTopDto> {
        return shellTopRepository.findTopById(id)
            .map(Function { top: Top? -> SeaShellTopDto.Companion.create(top) })
    }

    fun getLowerById(id: Long?): Mono<SeaShellLowerDto> {
        return shellLowerRepository.findLowerById(id)
            .map(Function { lower: Lower? -> SeaShellLowerDto.Companion.create(lower) })
    }

    private fun personPublisher(person: Person?): Mono<SeaShellPersonDto> {
        return Mono.zip(
            getAccountById(person!!.accountId).subscribeOn(Schedulers.parallel()),
            getCostumeById(person.costumeId).subscribeOn(Schedulers.parallel())
        ) { account: SeaShellAccountDto?, costume: SeaShellCostumeDto? ->
            SeaShellPersonDto.builder()
                .name(person.name)
                .accountId(person.accountId)
                .costumeId(person.costumeId)
                .accountDto(account)
                .costumeDto(costume)
                .build()
        }
    }

    private fun costumePublisher(costume: Costume?): Mono<SeaShellCostumeDto> {
        return Mono.zip(
            getTopById(costume!!.topId).subscribeOn(Schedulers.parallel()),
            getLowerById(costume.lowerId).subscribeOn(Schedulers.parallel())
        ) { top: SeaShellTopDto?, lower: SeaShellLowerDto? ->
            SeaShellCostumeDto.builder()
                .topId(costume.topId)
                .lowerId(costume.lowerId)
                .topDto(top)
                .lowerDto(lower)
                .build()
        }
    }
}