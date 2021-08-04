package org.jesperancinha.shell.webflux.immutable.repository;

import org.jesperancinha.shell.client.lowers.Lower;
import org.jesperancinha.shell.client.lowers.LowersClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellLowerImmutableRepository {

    private final LowersClient lowersClient;

    public ShellLowerImmutableRepository(LowersClient lowersClient) {
        this.lowersClient = lowersClient;
    }

    public Mono<Lower> findLowerById(final Long id) {
        return Mono.fromCallable(() -> lowersClient.getLower(id))
                .subscribeOn(single());
    }
}
