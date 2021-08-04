package org.jesperancinha.shell.webflux.immutable.repository;

import org.jesperancinha.shell.client.tops.Top;
import org.jesperancinha.shell.client.tops.TopsClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellTopRepositoryImpl {

    private final TopsClient topsClient;

    public ShellTopRepositoryImpl(TopsClient topsClient) {
        this.topsClient = topsClient;
    }

    public Mono<Top> findTopById(final Long id) {
        return Mono.fromCallable(() -> topsClient.getTop(id))
                .subscribeOn(single());
    }

    public Top findTopByIdBlock(final Long id) {
        return topsClient.getTop(id);
    }
}
