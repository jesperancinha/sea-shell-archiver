package org.jesperancinha.shell.webflux.immutable.repository

import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.persons.PersonsClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers

@Repository
class ShellPersonImmutableRepository(
    private val personsClient: PersonsClient,
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int
) {
    fun findPersonById(id: Long): Mono<Person> {
        return Mono.fromCallable { personsClient.getPerson(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findPersons(personIds: List<Long>): ParallelFlux<Person> {
        return Flux.fromIterable(personIds)
            .parallel(parallelism)
            .runOn(Schedulers.parallel())
            .map { personId -> personsClient.getPerson(personId) }
    }
}