package org.jesperancinha.shell.webflux.repo

import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.lowers.LowersClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class ShellLowerRepositoryImpl(private val lowersClient: LowersClient) {
    fun findLowerById(id: Long?): Mono<Lower> {
        return Mono.fromCallable { lowersClient.getLower(id) }
            .subscribeOn(Schedulers.single())
    }

    fun findLowerByIdBlock(lowerId: Long): Lower {
        return lowersClient.getLower(lowerId)
    }
}