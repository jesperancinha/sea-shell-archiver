package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.client.persons.SeaShellsWSDLPersonsAbstract;
import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsAbstract;
import org.jesperancinha.shell.client.shells.Shell;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;


@Repository
public class ShellPersonRepository {

    private final SeaShellsWSDLPersonsAbstract seaShellsWSDLPersonsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellPersonRepository(SeaShellsWSDLPersonsAbstract seaShellsWSDLPersonsClient) {
        this.seaShellsWSDLPersonsClient = seaShellsWSDLPersonsClient;
    }

    public Mono<Person> findSeaShellById(final Long id) {
        return Mono.just(seaShellsWSDLPersonsClient.getItem(id));
    }

    public ParallelFlux<Person> findPersons(List<Long> costumeIds) {
        return Flux.fromIterable(costumeIds)
                .parallel(parallelism)
                .runOn(Schedulers.elastic())
                .map(seaShellsWSDLPersonsClient::getItem);
    }
}
