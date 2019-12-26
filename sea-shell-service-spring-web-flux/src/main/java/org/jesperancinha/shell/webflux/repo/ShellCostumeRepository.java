package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.client.costumes.SeaShellsWSDLCostumesAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;


@Repository
public class ShellCostumeRepository {

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    private final SeaShellsWSDLCostumesAbstract seaShellsWSDLCostumesAbstract;

    public ShellCostumeRepository(SeaShellsWSDLCostumesAbstract seaShellsWSDLCostumesAbstract) {
        this.seaShellsWSDLCostumesAbstract = seaShellsWSDLCostumesAbstract;
    }

    public Mono<Costume> findCostumeById(final Long id) {
        return Mono.just(seaShellsWSDLCostumesAbstract.getItem(id));
    }

    public ParallelFlux<Costume> findCostumes(List<Long> costumeIds) {
        return Flux.fromIterable(costumeIds)
                .parallel(parallelism)
                .runOn(Schedulers.elastic())
                .map(seaShellsWSDLCostumesAbstract::getItem);
    }
}
