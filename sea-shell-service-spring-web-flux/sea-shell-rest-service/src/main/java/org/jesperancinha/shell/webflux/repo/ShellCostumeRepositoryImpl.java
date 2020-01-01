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
import java.util.stream.Collectors;


@Repository
public class ShellCostumeRepositoryImpl implements ShellCostumeRepository {

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    private final SeaShellsWSDLCostumesAbstract seaShellsWSDLCostumesAbstract;

    public ShellCostumeRepositoryImpl(SeaShellsWSDLCostumesAbstract seaShellsWSDLCostumesAbstract) {
        this.seaShellsWSDLCostumesAbstract = seaShellsWSDLCostumesAbstract;
    }

    public Mono<Costume> findCostumeById(final Long id) {
        return Mono.fromCallable(() -> seaShellsWSDLCostumesAbstract.getItem(id)).subscribeOn(Schedulers.elastic());
    }

    public ParallelFlux<Costume> findCostumes(List<Long> costumeIds) {
        return Mono
                .fromCallable(() -> costumeIds.parallelStream()
                        .map(seaShellsWSDLCostumesAbstract::getItem)
                        .collect(Collectors.toList()))
                .flux().flatMap(Flux::fromIterable)
                .parallel(parallelism)
                .runOn(Schedulers.elastic());
    }

    public List<Costume> findCostumesBlock(List<Long> costumeIds) {
        return costumeIds.parallelStream()
                .map(seaShellsWSDLCostumesAbstract::getItem)
                .collect(Collectors.toList());
    }

    public Costume findCostumeByIdBlock(Long costumeId) {
        return seaShellsWSDLCostumesAbstract.getItem(costumeId);
    }
}
