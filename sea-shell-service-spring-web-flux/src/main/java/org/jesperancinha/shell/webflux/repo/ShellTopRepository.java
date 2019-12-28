package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.tops.SeaShellsWSDLTopAbstract;
import org.jesperancinha.shell.client.tops.Top;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Repository
public class ShellTopRepository {

    private final SeaShellsWSDLTopAbstract seaShellsWSDLTopClient;

    public ShellTopRepository(SeaShellsWSDLTopAbstract seaShellsWSDLTopClient) {
        this.seaShellsWSDLTopClient = seaShellsWSDLTopClient;
    }

    public Mono<Top> findTopById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLTopClient.getItem(id))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Top findTopByIdBlock(final Long id) {
        return seaShellsWSDLTopClient.getItem(id);
    }
}
