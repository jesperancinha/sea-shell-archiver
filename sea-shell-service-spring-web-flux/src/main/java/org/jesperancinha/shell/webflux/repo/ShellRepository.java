package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.webflux.model.SeaShell;
import org.jesperancinha.shell.webflux.model.SeaShellLocation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class ShellRepository {

    public Mono<SeaShell> findSeaShellById(final Long id) {
        return null;
    }

    public Flux<SeaShell> findAllSeaShells() {
        return null;
    }

    public Mono<SeaShell> updateSeaShell(SeaShell seaShell) {
       return null;
    }

    public List<SeaShellLocation> findAllLocations(List<Long> seaShellLocationListIds) {
        return null;
    }
}
