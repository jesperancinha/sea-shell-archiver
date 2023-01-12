package org.jesperancinha.shell.webflux.immutable.repository

import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.client.tops.TopsClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class ShellTopImmutableRepository(private val topsClient: TopsClient) {
    fun findTopById(id: Long?): Mono<Top?> {
        return Mono.fromCallable { topsClient.getTop(id) }
            .subscribeOn(Schedulers.single())
    }
}