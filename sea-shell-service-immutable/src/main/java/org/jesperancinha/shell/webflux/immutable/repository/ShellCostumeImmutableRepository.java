package org.jesperancinha.shell.webflux.immutable.repository;

import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.client.costumes.CostumesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.List;


@Repository
public class ShellCostumeImmutableRepository {

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    private final CostumesClient costumesClient;

    public ShellCostumeImmutableRepository(CostumesClient costumesClient) {
        this.costumesClient = costumesClient;
    }

    public Mono<Costume> findCostumeById(final Long id) {
        return Mono.fromCallable(() -> costumesClient.getCostume(id)).subscribeOn(Schedulers.boundedElastic());
    }

    public ParallelFlux<Costume> findCostumes(List<Long> costumeIds) {

        return Flux.fromIterable(costumeIds)
                .parallel(parallelism)
                .runOn(Schedulers.parallel())
                .map(costumeId -> Mono.fromCallable(() -> costumesClient.getCostume(costumeId)))
                .flatMap(ParallelFlux::from);
    }
}
