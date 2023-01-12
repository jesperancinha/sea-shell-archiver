package org.jesperancinha.shell.webflux.repo

import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.accounts.AccountsClient
import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.costumes.CostumesClient
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.lowers.LowersClient
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.persons.PersonsClient
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.shells.ShellsClient
import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.client.tops.TopsClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers
import java.util.stream.Collectors

@Repository
class ShellAccountRepository(
    private val accountsClient: AccountsClient,
) {

    fun findAccountById(id: String): Mono<Account> {
        return Mono.fromCallable { accountsClient.getAccount(id) }
            .subscribeOn(Schedulers.single())
    }

    fun findAccountByIdBlock(id: String): Account {
        return accountsClient.getAccount(id)
    }
}

@Repository
class ShellCostumeRepository(
    private val costumesClient: CostumesClient,
    @Value("\${sea.shell.parallelism:20}")
    val parallelism: Int
) {

    fun findCostumeById(id: Long): Mono<Costume> {
        return Mono.fromCallable { costumesClient.getCostume(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findCostumes(costumeIds: List<Long>): ParallelFlux<Costume> {
        return Flux.fromIterable(costumeIds)
            .parallel(parallelism)
            .runOn(Schedulers.parallel())
            .map { costumeId-> Mono.fromCallable { costumesClient.getCostume(costumeId) } }
            .flatMap { source -> ParallelFlux.from(source) }
    }

    fun findCostumesBlock(costumeIds: List<Long>): List<Costume> {
        return costumeIds.parallelStream()
            .map { costumeId-> costumesClient.getCostume(costumeId) }
            .collect(Collectors.toList())
    }

    fun findCostumeByIdBlock(costumeId: Long): Costume {
        return costumesClient.getCostume(costumeId)
    }
}

@Repository
class ShellLowerRepository(private val lowersClient: LowersClient) {
    fun findLowerById(id: Long): Mono<Lower> {
        return Mono.fromCallable { lowersClient.getLower(id) }
            .subscribeOn(Schedulers.single())
    }

    fun findLowerByIdBlock(lowerId: Long): Lower {
        return lowersClient.getLower(lowerId)
    }
}

@Repository
class ShellPersonRepository(
    private val personsClient: PersonsClient,
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int
) {

    fun findPersonById(id: Long?): Mono<Person> {
        return Mono.fromCallable { personsClient.getPerson(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findPersons(personIds: List<Long>): ParallelFlux<Person> {
        return Flux.fromIterable(personIds)
            .parallel(parallelism)
            .runOn(Schedulers.parallel())
            .map { personId: Long? -> Mono.fromCallable { personsClient.getPerson(personId) } }
            .flatMap { source -> ParallelFlux.from(source) }
    }

    fun findPersonsBlock(personIds: List<Long>): List<Person> {
        return personIds.parallelStream()
            .map { personId: Long? -> personsClient.getPerson(personId) }
            .collect(Collectors.toList())
    }
}

@Repository
@ConditionalOnBean(ShellsClient::class)
class ShellRepository(
    private val shellsClient: ShellsClient,
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int
) {

    fun findSeaShellById(id: Long): Mono<Shell> {
        return Mono.fromCallable { shellsClient.getShell(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findAllSeaShells(): ParallelFlux<Shell> {
        return findAllShellIds()
            .parallel(parallelism)
            .runOn(Schedulers.boundedElastic())
            .map { id: Long? -> shellsClient.getShell(id) }
            .runOn(Schedulers.single())
    }

    fun findAllShellIds(): Flux<Long> {
        return Mono.fromCallable { shellsClient.allShellIds }
            .flux().flatMap { Flux.fromIterable(it) }
    }

    fun findAllSeaShellsBlock(): List<Shell> {
        return shellsClient.allShellIds.parallelStream()
            .map { id-> shellsClient.getShell(id) }
            .collect(Collectors.toList())
    }

    fun findSeaShellBlockById(id: Long?): Shell {
        return shellsClient.getShell(id)
    }
}

@Repository
class ShellTopRepository(private val topsClient: TopsClient) {
    fun findTopById(id: Long): Mono<Top> {
        return Mono.fromCallable { topsClient.getTop(id) }
            .subscribeOn(Schedulers.single())
    }

    fun findTopByIdBlock(id: Long): Top {
        return topsClient.getTop(id)
    }
}