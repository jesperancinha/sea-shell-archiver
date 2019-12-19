package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.webflux.model.SeaShell;
import org.jesperancinha.shell.webflux.model.SeaShellLocation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.List.of;
import static org.jesperancinha.shell.webflux.model.ShellState.Optimal;
import static org.jesperancinha.shell.webflux.model.ShellType.Bivalvia;


/**
 * This repository is just a simulation of an actual client JSON repo.
 * At this point we just want to see what happens further up the chain after having made JSON requests and joining them
 */
@Repository
public class ShellRepository {
    public static Map<Long, SeaShell> SEA_SHELL_MAP = new HashMap<>();
    public static Map<Long, SeaShellLocation> SEA_SHELL_LOCATION = new HashMap<>();

    static {
        SEA_SHELL_LOCATION.put(1L,
                SeaShellLocation.builder().designation("Eisbrecher bar").lan(BigDecimal.valueOf(111111)).lon(BigDecimal.valueOf(22222)).build());
        SEA_SHELL_LOCATION.put(2L,
                SeaShellLocation.builder().designation("Vergissmeinnicht bar").lan(BigDecimal.valueOf(333333)).lon(BigDecimal.valueOf(444444)).build());


        SEA_SHELL_MAP.put(1L,
                SeaShell.builder().id(1L).commonName("ugabuga").scientificName("ugalibis").currency("EUR").value(BigDecimal.valueOf(100)).shellState(Optimal).shellType(Bivalvia).seaShellLocationListIds(of(1L)).build());
        SEA_SHELL_MAP.put(2L,
                SeaShell.builder().id(2L).commonName("bagabuga").scientificName("bugalibis").currency("EUR").value(BigDecimal.valueOf(100)).shellState(Optimal).shellType(Bivalvia).seaShellLocationListIds(of(2L)).build());
    }

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
