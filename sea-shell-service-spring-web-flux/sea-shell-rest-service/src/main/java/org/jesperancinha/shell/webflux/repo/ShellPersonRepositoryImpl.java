package org.jesperancinha.shell.webflux.repo;

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
import static reactor.core.scheduler.Schedulers.boundedElastic;


@Repository
public class ShellPersonRepositoryImpl implements ShellPersonRepository {

    private final PersonsClient personsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellPersonRepositoryImpl(PersonsClient personsClient) {
        this.personsClient = personsClient;
    }

    public Mono<Person> findPersonById(final Long id) {
        return fromCallable(() -> personsClient.getPerson(id)).subscribeOn(boundedElastic());
    }

    public ParallelFlux<Person> findPersons(List<Long> personIds) {
        return Flux.fromIterable(personIds)
                .parallel(parallelism)
                .runOn(Schedulers.parallel())
                .map(personId -> fromCallable(() -> personsClient.getPerson(personId)))
                .flatMap(ParallelFlux::from);

    }

    public List<Person> findPersonsBlock(List<Long> personIds) {
        return personIds.parallelStream()
                .map(personsClient::getPerson)
                .collect(Collectors.toList());
    }
}
