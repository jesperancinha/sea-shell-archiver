package org.jesperancinha.shell.webflux.immutable.repository;

import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.client.persons.PersonsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

import static reactor.core.publisher.Mono.fromCallable;
import static reactor.core.scheduler.Schedulers.elastic;


@Repository
public class ShellPersonImmutableRepository{

    private final PersonsClient personsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellPersonImmutableRepository(PersonsClient personsClient) {
        this.personsClient = personsClient;
    }

    public Mono<Person> findPersonById(final Long id) {
        return fromCallable(() -> personsClient.getPerson(id)).subscribeOn(elastic());
    }

    public ParallelFlux<Person> findPersons(List<Long> personIds) {
        return Flux.fromIterable(personIds)
                .parallel(parallelism)
                .runOn(Schedulers.parallel())
                .map(personId -> fromCallable(() -> personsClient.getPerson(personId)))
                .flatMap(ParallelFlux::from);

    }
}
