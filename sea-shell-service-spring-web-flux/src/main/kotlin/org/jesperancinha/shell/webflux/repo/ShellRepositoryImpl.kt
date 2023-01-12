package org.jesperancinha.shell.webflux.repo

import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.shells.ShellsClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers
import java.util.stream.Collectors

@Repository
@ConditionalOnBean(ShellsClient::class)
class ShellRepositoryImpl(private val shellsClient: ShellsClient) {
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int? = null
    fun findSeaShellById(id: Long): Mono<Shell> {
        return Mono.fromCallable { shellsClient.getShell(id) }.subscribeOn(Schedulers.boundedElastic())
    }

    fun findAllSeaShells(): ParallelFlux<Shell> {
        return findAllShellIds()
            .parallel(parallelism!!)
            .runOn(Schedulers.boundedElastic())
            .map { id: Long? -> shellsClient.getShell(id) }
            .runOn(Schedulers.single())
    }

    fun findAllShellIds(): Flux<Long> {
        return Mono.fromCallable { shellsClient.allShellIds }
            .flux().flatMap { it: List<Long>? -> Flux.fromIterable(it) }
    }

    fun findAllSeaShellsBlock(): List<Shell> {
        return shellsClient.allShellIds.parallelStream().map { id: Long? -> shellsClient.getShell(id) }
            .collect(Collectors.toList())
    }

    fun findSeaShellBlockById(id: Long?): Shell {
        return shellsClient.getShell(id)
    }
}