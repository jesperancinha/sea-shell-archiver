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

/**
 * Repository to get complete Shell Data
 */
@Repository
public class ShellRepository {
    public static Map<Long, SeaShell> SEA_SHELL_MAP = new HashMap<>();
    public static Map<Long, SeaShellLocation> SEA_SHELL_LOCATION = new HashMap<>();

    public Mono<SeaShell> findSeaShellById(final Long id) {
        return Mono.just(SEA_SHELL_MAP.get(id));
    }

    public Flux<SeaShell> findAllSeaShells() {
        return Flux.fromIterable(SEA_SHELL_MAP.values());
    }

    public Mono<SeaShell> updateSeaShell(SeaShell seaShell) {
        final Long id = seaShell.getId();
        SEA_SHELL_MAP.put(id, seaShell);
        return Mono.just(SEA_SHELL_MAP.get(id));
    }

    public List<SeaShellLocation> findAllLocations(List<Long> seaShellLocationListIds) {
        return seaShellLocationListIds.stream().map(id -> SEA_SHELL_LOCATION.get(id)).collect(Collectors.toList());
    }
}
