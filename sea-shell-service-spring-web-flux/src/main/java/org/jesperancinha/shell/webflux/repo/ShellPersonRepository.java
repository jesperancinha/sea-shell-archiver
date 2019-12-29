package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.client.persons.SeaShellsWSDLPersonsAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ShellPersonRepository {

    private final SeaShellsWSDLPersonsAbstract seaShellsWSDLPersonsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellPersonRepository(SeaShellsWSDLPersonsAbstract seaShellsWSDLPersonsClient) {
        this.seaShellsWSDLPersonsClient = seaShellsWSDLPersonsClient;
    }

    public Mono<Person> findPersonById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLPersonsClient.getItem(id)).subscribeOn(Schedulers.boundedElastic());
    }

    public ParallelFlux<Person> findPersons(List<Long> costumeIds) {
        return Mono
                .fromCallable(() -> costumeIds.parallelStream()
                        .map(seaShellsWSDLPersonsClient::getItem)
                        .collect(Collectors.toList()))
                .flux().flatMap(Flux::fromIterable)
                .parallel(parallelism)
                .runOn(Schedulers.boundedElastic());
    }

    public List<Person> findPersonsBlock(List<Long> costumeIds) {
        return costumeIds.parallelStream()
                .map(seaShellsWSDLPersonsClient::getItem)
                .collect(Collectors.toList());
    }
}
