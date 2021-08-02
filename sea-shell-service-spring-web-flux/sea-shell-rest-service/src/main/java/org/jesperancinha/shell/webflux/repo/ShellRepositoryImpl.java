package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.client.shells.ShellsClient;
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

    private final ShellsClient shellsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellRepositoryImpl(ShellsClient shellsClient) {
        this.shellsClient = shellsClient;
    }

    public Mono<Shell> findSeaShellById(final Long id) {
        return Mono.fromCallable(() -> shellsClient.getShell(id)).subscribeOn(elastic());
    }

    public ParallelFlux<Shell> findAllSeaShells() {
        return findAllShellIds()
                .parallel(parallelism)
                .runOn(elastic())
                .map(shellsClient::getShell)
                .runOn(single());
    }

    public Flux<Long> findAllShellIds() {
        return Mono.fromCallable(shellsClient::getAllShellIds)
                .flux().flatMap(Flux::fromIterable);
    }

    public List<Shell> findAllSeaShellsBlock() {
        return shellsClient.getAllShellIds().parallelStream().map(shellsClient::getShell).collect(Collectors.toList());
    }

    public Shell findSeaShellBlockById(Long id) {
        return shellsClient.getShell(id);
    }
}
