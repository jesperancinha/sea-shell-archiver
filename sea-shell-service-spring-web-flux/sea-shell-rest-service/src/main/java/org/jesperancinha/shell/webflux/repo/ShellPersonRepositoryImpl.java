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

import static reactor.core.publisher.Mono.fromCallable;
import static reactor.core.scheduler.Schedulers.elastic;


@Repository
public class ShellPersonRepositoryImpl implements ShellPersonRepository {

    private final SeaShellsWSDLPersonsAbstract seaShellsWSDLPersonsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellPersonRepositoryImpl(SeaShellsWSDLPersonsAbstract seaShellsWSDLPersonsClient) {
        this.seaShellsWSDLPersonsClient = seaShellsWSDLPersonsClient;
    }

    public Mono<Person> findPersonById(final Long id) {
        return fromCallable(() -> seaShellsWSDLPersonsClient.getItem(id)).subscribeOn(elastic());
    }

    public ParallelFlux<Person> findPersons(List<Long> personIds) {
        return Flux.fromIterable(personIds)
                .parallel(parallelism)
                .runOn(Schedulers.parallel())
                .map(personId -> fromCallable(() -> seaShellsWSDLPersonsClient.getItem(personId)))
                .flatMap(ParallelFlux::from);

    }

    public List<Person> findPersonsBlock(List<Long> personIds) {
        return personIds.parallelStream()
                .map(seaShellsWSDLPersonsClient::getItem)
                .collect(Collectors.toList());
    }
}
