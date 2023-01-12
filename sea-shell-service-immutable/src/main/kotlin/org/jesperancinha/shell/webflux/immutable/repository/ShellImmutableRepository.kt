package org.jesperancinha.shell.webflux.immutable.repository

import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.shells.ShellsClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers

@Repository
@ConditionalOnBean(ShellsClient::class)
class ShellImmutableRepository(private val shellsClient: ShellsClient) {
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int? = null
    fun findSeaShellById(id: Long?): Mono<Shell?> {
        return Mono.fromCallable { shellsClient.getShell(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findAllSeaShells(): ParallelFlux<Shell> {
        return findAllShellIds()
            .parallel(parallelism!!)
            .runOn(Schedulers.boundedElastic())
            .map { id: Long? -> shellsClient.getShell(id) }
            .runOn(Schedulers.single())
    }

    fun findAllShellIds(): Flux<Long?> {
        return Mono.fromCallable { shellsClient.allShellIds }
            .flux().flatMap { it: List<Long>? -> Flux.fromIterable(it) }
    }
}