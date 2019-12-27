package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.tops.SeaShellsWSDLTopAbstract;
import org.jesperancinha.shell.client.tops.Top;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class ShellTopRepository {

    private final SeaShellsWSDLTopAbstract seaShellsWSDLTopClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellTopRepository(SeaShellsWSDLTopAbstract seaShellsWSDLTopClient) {
        this.seaShellsWSDLTopClient = seaShellsWSDLTopClient;
    }

    public Mono<Top> findTopById(final Long id) {
        return Mono.just(seaShellsWSDLTopClient.getItem(id));
    }
}
