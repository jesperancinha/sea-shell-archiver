package org.jesperancinha.ssa.webflux.repo;

import static org.jesperancinha.ssa.webflux.model.ShellState.Optimal;
import static org.jesperancinha.ssa.webflux.model.ShellType.Bivalvia;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.jesperancinha.ssa.webflux.model.SeaShell;
import org.jesperancinha.ssa.webflux.model.SeaShellLocation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * This repository is just a simulation of an actual client JSON repo.
 * At this point we just want to see what happens further up the chain after having made JSON requests and joining them
 */
@Repository
public class ShellRepository
{
    private static Map<Long, SeaShell> seaShellMap = new HashMap<>();
    private static Map<Long, SeaShellLocation> seaShellLocationMap = new HashMap<>();

    static
    {
        seaShellLocationMap.put(1L,
                SeaShellLocation.builder().designation("Eisbrecher bar").lan(BigDecimal.valueOf(111111)).lon(BigDecimal.valueOf(22222)).build());
        seaShellLocationMap.put(2L,
                SeaShellLocation.builder().designation("Vergissmeinnicht bar").lan(BigDecimal.valueOf(333333)).lon(BigDecimal.valueOf(444444)).build());


        seaShellMap.put(1L,
                SeaShell.builder().commonName("ugabuga").scientificName("ugalibis").currency("EUR").value(BigDecimal.valueOf(100)).shellState(Optimal).shellType(Bivalvia).build());
        seaShellMap.put(2L,
                SeaShell.builder().commonName("bagabuga").scientificName("bugalibis").currency("EUR").value(BigDecimal.valueOf(100)).shellState(Optimal).shellType(Bivalvia).build());
    }

    public Mono<SeaShell> findSeaShellById(final Long id)
    {
        return Mono.just(seaShellMap.get(id));
    }

    public Flux<SeaShell> findAllSeaShells()
    {
        return Flux.fromIterable(seaShellMap.values());
    }

    public Mono<SeaShell> updateEmployee(SeaShell seaShell)
    {
        final Long id = seaShell.getId();
        seaShellMap.put(id, seaShell);
        return Mono.just(seaShellMap.get(id));
    }
}
