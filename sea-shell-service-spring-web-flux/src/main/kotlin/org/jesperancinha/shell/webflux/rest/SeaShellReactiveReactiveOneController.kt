package org.jesperancinha.shell.webflux.rest

import org.jesperancinha.shell.webflux.data.*
import org.jesperancinha.shell.webflux.service.SeaShellReactiveOneServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/seashells/one")
class SeaShellReactiveReactiveOneController(private val seaShellReactiveOneService: SeaShellReactiveOneServiceImpl) {
    @GetMapping
    fun allShells(): Flux<Long> = seaShellReactiveOneService.allIds()

    @GetMapping("/{id}")
    fun getShellById(
        @PathVariable id: Long
    ): Mono<SeaShellDto> {
        return seaShellReactiveOneService.getSeaShellById(id)
    }

    @GetMapping("/person/{id}")
    fun getPersonById(
        @PathVariable id: Long
    ): Mono<SeaShellPersonDto>{
        return seaShellReactiveOneService.getPersonById(id)
    }

    @GetMapping("/costume/{id}")
    fun getCostumeById(
        @PathVariable id: Long
    ): Mono<SeaShellCostumeDto> {
        return seaShellReactiveOneService.getCostumeById(id)
    }

    @GetMapping("/account/{id}")
    fun getAccountById(
        @PathVariable id: String
    ): Mono<SeaShellAccountDto> {
        return seaShellReactiveOneService.getAccountById(id)
    }

    @GetMapping("/top/{id}")
    fun getTopById(
        @PathVariable id: Long
    ): Mono<SeaShellTopDto> {
        return seaShellReactiveOneService.getTopById(id)
    }

    @GetMapping("/lower/{id}")
    fun getLowerById(
        @PathVariable id: Long
    ): Mono<SeaShellLowerDto> {
        return seaShellReactiveOneService.getLowerById(id)
    }
}