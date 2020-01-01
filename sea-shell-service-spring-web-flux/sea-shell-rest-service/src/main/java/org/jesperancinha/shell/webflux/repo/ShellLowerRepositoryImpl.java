package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.lowers.Lower;
import org.jesperancinha.shell.client.lowers.SeaShellsWSDLLowersAbstract;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellLowerRepositoryImpl implements ShellLowerRepository {

    private final SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient;

    public ShellLowerRepositoryImpl(SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient) {
        this.seaShellsWSDLLowersClient = seaShellsWSDLLowersClient;
    }

    public Mono<Lower> findLowerById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLLowersClient.getItem(id))
                .subscribeOn(single());
    }

    public Lower findLowerByIdBlock(Long lowerId) {
        return seaShellsWSDLLowersClient.getItem(lowerId);
    }
}
