package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsAbstract;
import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class ShellRepository {

    private final SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellRepository(SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient) {
        this.seaShellsWSDLShellsClient = seaShellsWSDLShellsClient;
    }

    public Mono<Shell> findSeaShellById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLShellsClient.getItem(id)).subscribeOn(Schedulers.boundedElastic());
    }

    public ParallelFlux<Shell> findAllSeaShells() {
        return Mono.fromCallable(seaShellsWSDLShellsClient::getAllShellIds)
                .flux().flatMap(Flux::fromIterable)
                .parallel(parallelism)
                .runOn(Schedulers.boundedElastic())
                .map(seaShellsWSDLShellsClient::getItem)
                .runOn(Schedulers.boundedElastic());
    }

    public List<Shell> findAllSeaShellsBlock() {
        return seaShellsWSDLShellsClient.getAllShellIds().parallelStream().map(seaShellsWSDLShellsClient::getItem).collect(Collectors.toList());
    }
}
