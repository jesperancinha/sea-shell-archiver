package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.lowers.Lower;
import org.jesperancinha.shell.client.lowers.LowersClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellLowerRepositoryImpl implements ShellLowerRepository {

    private final LowersClient lowersClient;

    public ShellLowerRepositoryImpl(LowersClient lowersClient) {
        this.lowersClient = lowersClient;
    }

    public Mono<Lower> findLowerById(final Long id) {
        return Mono.fromCallable(() -> lowersClient.getlLower(id))
                .subscribeOn(single());
    }

    public Lower findLowerByIdBlock(Long lowerId) {
        return lowersClient.getlLower(lowerId);
    }
}
