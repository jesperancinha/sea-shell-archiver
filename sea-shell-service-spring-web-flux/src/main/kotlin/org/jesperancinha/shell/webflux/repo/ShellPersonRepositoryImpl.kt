package org.jesperancinha.shell.webflux.repo

import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.persons.PersonsClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers
import java.util.stream.Collectors

@Repository
class ShellPersonRepositoryImpl(private val personsClient: PersonsClient) {
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int? = null
    fun findPersonById(id: Long?): Mono<Person?> {
        return Mono.fromCallable { personsClient.getPerson(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findPersons(personIds: List<Long>?): ParallelFlux<Person?> {
        return Flux.fromIterable(personIds)
            .parallel(parallelism!!)
            .runOn(Schedulers.parallel())
            .map { personId: Long? -> Mono.fromCallable { personsClient.getPerson(personId) } }
            .flatMap { source: Mono<Person>? -> ParallelFlux.from(source) }
    }

    fun findPersonsBlock(personIds: List<Long>): List<Person?> {
        return personIds.parallelStream()
            .map { personId: Long? -> personsClient.getPerson(personId) }
            .collect(Collectors.toList())
    }
}