package org.jesperancinha.shell.webflux.immutable.repository

import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.costumes.CostumesClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers

@Repository
class ShellCostumeImmutableRepository(
    private val costumesClient: CostumesClient,
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int
) {

    fun findCostumeById(id: Long): Mono<Costume> {
        return Mono.fromCallable { costumesClient.getCostume(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findCostumes(costumeIds: List<Long>): ParallelFlux<Costume> {
        return Flux.fromIterable(costumeIds)
            .parallel(parallelism)
            .runOn(Schedulers.parallel())
            .map { costumeId -> costumesClient.getCostume(costumeId) }
    }
}