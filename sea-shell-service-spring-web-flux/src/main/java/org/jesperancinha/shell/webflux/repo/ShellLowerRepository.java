package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.lowers.Lower;
import org.jesperancinha.shell.client.lowers.SeaShellsWSDLLowersAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class ShellLowerRepository {

    private final SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellLowerRepository(SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient) {
        this.seaShellsWSDLLowersClient = seaShellsWSDLLowersClient;
    }

    public Mono<Lower> findLowerById(final Long id) {
        return Mono.just(seaShellsWSDLLowersClient.getItem(id));
    }

}
