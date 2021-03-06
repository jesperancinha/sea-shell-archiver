package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsAbstract;
import org.jesperancinha.shell.client.shells.Shell;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;
import java.util.stream.Collectors;

import static reactor.core.scheduler.Schedulers.elastic;
import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellRepositoryImpl implements ShellRepository {

    private final SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellRepositoryImpl(SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient) {
        this.seaShellsWSDLShellsClient = seaShellsWSDLShellsClient;
    }

    public Mono<Shell> findSeaShellById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLShellsClient.getItem(id)).subscribeOn(elastic());
    }

    public ParallelFlux<Shell> findAllSeaShells() {
        return findAllShellIds()
                .parallel(parallelism)
                .runOn(elastic())
                .map(seaShellsWSDLShellsClient::getItem)
                .runOn(single());
    }

    public Flux<Long> findAllShellIds() {
        return Mono.fromCallable(seaShellsWSDLShellsClient::getAllShellIds)
                .flux().flatMap(Flux::fromIterable);
    }

    public List<Shell> findAllSeaShellsBlock() {
        return seaShellsWSDLShellsClient.getAllShellIds().parallelStream().map(seaShellsWSDLShellsClient::getItem).collect(Collectors.toList());
    }

    public Shell findSeaShellBlockById(Long id) {
        return seaShellsWSDLShellsClient.getItem(id);
    }
}
