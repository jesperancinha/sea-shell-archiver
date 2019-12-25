package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsClient;
import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Repository
public class ShellRepository {

    private final SeaShellsWSDLShellsClient seaShellsWSDLShellsClient;

    public ShellRepository(SeaShellsWSDLShellsClient seaShellsWSDLShellsClient) {
        this.seaShellsWSDLShellsClient = seaShellsWSDLShellsClient;
    }

    public Mono<Shell> findSeaShellById(final Long id) {
        return Mono.just(seaShellsWSDLShellsClient.getItem(id));
    }

    public Flux<SeaShellDto> findAllSeaShells() {
        return null;
    }

    public Mono<SeaShellDto> updateSeaShell(SeaShellDto seaShell) {
        return null;
    }

    public List<SeaShellDto> findAllLocations(List<Long> seaShellLocationListIds) {
        return null;
    }
}
