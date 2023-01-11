package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.costumes.Costume;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

public interface ShellCostumeRepository {
    Mono<Costume> findCostumeById(Long id);

    ParallelFlux<Costume> findCostumes(List<Long> costumeIds);

    List<Costume> findCostumesBlock(List<Long> costumeIds);

    Costume findCostumeByIdBlock(Long costumeId);
}
