package org.jesperancinha.shell.webflux.repo

import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.costumes.CostumesClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers
import java.util.stream.Collectors

@Repository
class ShellCostumeRepositoryImpl(private val costumesClient: CostumesClient) {
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int? = null
    fun findCostumeById(id: Long?): Mono<Costume?> {
        return Mono.fromCallable { costumesClient.getCostume(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findCostumes(costumeIds: List<Long>?): ParallelFlux<Costume?> {
        return Flux.fromIterable(costumeIds)
            .parallel(parallelism!!)
            .runOn(Schedulers.parallel())
            .map { costumeId: Long? -> Mono.fromCallable { costumesClient.getCostume(costumeId) } }
            .flatMap { source: Mono<Costume>? -> ParallelFlux.from(source) }
    }

    fun findCostumesBlock(costumeIds: List<Long>): List<Costume?> {
        return costumeIds.parallelStream()
            .map { costumeId: Long? -> costumesClient.getCostume(costumeId) }
            .collect(Collectors.toList())
    }

    fun findCostumeByIdBlock(costumeId: Long?): Costume {
        return costumesClient.getCostume(costumeId)
    }
}