package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.lowers.Lower;
import org.jesperancinha.shell.client.lowers.SeaShellsWSDLLowersAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Repository
public class ShellLowerRepository {

    private final SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient;

    public ShellLowerRepository(SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient) {
        this.seaShellsWSDLLowersClient = seaShellsWSDLLowersClient;
    }

    public Mono<Lower> findLowerById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLLowersClient.getItem(id))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Lower findLowerByIdBlock(Long lowerId) {
        return seaShellsWSDLLowersClient.getItem(lowerId);
    }
}
