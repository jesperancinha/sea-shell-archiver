package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsAbstract;
import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsClient;
import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ShellRepository {

    private final SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient;

    int wew = 2;

    public ShellRepository(SeaShellsWSDLShellsClient seaShellsWSDLShellsClient) {
        this.seaShellsWSDLShellsClient = seaShellsWSDLShellsClient;
    }

    public Mono<Shell> findSeaShellById(final Long id) {
        return Mono.just(seaShellsWSDLShellsClient.getItem(id));
    }

    public Flux<Shell> findAllSeaShells() {
        return Flux.fromIterable(seaShellsWSDLShellsClient.getAllShellIds()).map(seaShellsWSDLShellsClient::getItem).doOnNext(t->t.setSlogan(String.valueOf(wew++)));
    }

    public Mono<SeaShellDto> updateSeaShell(SeaShellDto seaShell) {
        return null;
    }

    public List<SeaShellDto> findAllLocations(List<Long> seaShellLocationListIds) {
        return null;
    }
}
