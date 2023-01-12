package org.jesperancinha.shell.webflux.immutable.rest

import org.jesperancinha.shell.webflux.immutable.data.*
import org.jesperancinha.shell.webflux.immutable.service.SeaShellsReactiveImmutableService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/seashells/immutable")
class SeaShellReactiveImmutableController(private val seaShellsReactiveImmutableService: SeaShellsReactiveImmutableService) {
    @get:GetMapping
    val allShells: Flux<SeaShellDto?>?
        get() = seaShellsReactiveImmutableService.allShells

    @GetMapping("/{id}")
    fun getShellById(
        @PathVariable id: Long?
    ): Mono<SeaShellDto?>? {
        return seaShellsReactiveImmutableService.getSeaShellById(id)
    }

    @GetMapping("/person/{id}")
    fun getPersonById(
        @PathVariable id: Long?
    ): Mono<SeaShellPersonDto?>? {
        return seaShellsReactiveImmutableService.getPersonById(id)
    }

    @GetMapping("/costume/{id}")
    fun getCostumeById(
        @PathVariable id: Long?
    ): Mono<SeaShellCostumeDto?>? {
        return seaShellsReactiveImmutableService.getCostumeById(id)
    }

    @GetMapping("/account/{id}")
    fun getAccountById(
        @PathVariable id: String?
    ): Mono<SeaShellAccountDto?>? {
        return seaShellsReactiveImmutableService.getAccountById(id)
    }

    @GetMapping("/top/{id}")
    fun getTopById(
        @PathVariable id: Long?
    ): Mono<SeaShellTopDto?>? {
        return seaShellsReactiveImmutableService.getTopById(id)
    }

    @GetMapping("/lower/{id}")
    fun getLowerById(
        @PathVariable id: Long?
    ): Mono<SeaShellLowerDto?>? {
        return seaShellsReactiveImmutableService.getLowerById(id)
    }
}