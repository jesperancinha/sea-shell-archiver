package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsAbstract;
import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsClient;
import org.jesperancinha.shell.client.shells.Shell;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;


@Repository
public class ShellTopRepository {

    private final SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellTopRepository(SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient) {
        this.seaShellsWSDLShellsClient = seaShellsWSDLShellsClient;
    }

    public Mono<Shell> findSeaShellById(final Long id) {
        return Mono.just(seaShellsWSDLShellsClient.getItem(id));
    }

    public ParallelFlux<Shell> findAllSeaShells() {
        return Flux.fromIterable(seaShellsWSDLShellsClient.getAllShellIds())
                .parallel(parallelism)
                .runOn(Schedulers.elastic())
                .map(seaShellsWSDLShellsClient::getItem);
    }
}
