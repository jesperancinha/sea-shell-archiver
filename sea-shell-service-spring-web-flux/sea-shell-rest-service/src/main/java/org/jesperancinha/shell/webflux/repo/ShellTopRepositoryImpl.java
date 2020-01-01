package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.tops.SeaShellsWSDLTopAbstract;
import org.jesperancinha.shell.client.tops.Top;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellTopRepositoryImpl implements ShellTopRepository {

    private final SeaShellsWSDLTopAbstract seaShellsWSDLTopClient;

    public ShellTopRepositoryImpl(SeaShellsWSDLTopAbstract seaShellsWSDLTopClient) {
        this.seaShellsWSDLTopClient = seaShellsWSDLTopClient;
    }

    public Mono<Top> findTopById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLTopClient.getItem(id))
                .subscribeOn(single());
    }

    public Top findTopByIdBlock(final Long id) {
        return seaShellsWSDLTopClient.getItem(id);
    }
}
