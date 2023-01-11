package org.jesperancinha.shell.webflux.rest

import org.jesperancinha.shell.webflux.data.SeaShellDto
import org.jesperancinha.shell.webflux.service.SeaShellServiceImpl
import org.springframework.data.util.Pair
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux

@RestController
@RequestMapping("/seashells")
class SeaShellControllerImpl(private val seaShellService: SeaShellServiceImpl) {
    @GetMapping(path = ["/{id}"])
    fun getShellById(@PathVariable id: Long?): Mono<SeaShellDto?> {
        return seaShellService.getSeaShellById(id)
            .mapNotNull { seaShellDto: SeaShellDto? -> ResponseEntity.ok(seaShellDto).body }
    }

    @get:GetMapping
    val allShells: ParallelFlux<SeaShellDto?>?
        get() = seaShellService.allSeaShells

    @get:GetMapping("/slogans")
    val shellSlogans: ParallelFlux<Pair<String, String>>
        /**
         * Just the slogans
         *
         * @return
         */
        get() = seaShellService.allSeaShells.map { seaShellDto: SeaShellDto? ->
            Pair.of(
                seaShellDto!!.name, seaShellDto.slogan
            )
        }

    @get:GetMapping("/block")
    val allShellsBlock: List<SeaShellDto?>?
        /**
         * Blocking solution
         *
         * @return
         */
        get() = seaShellService.allSeaShellsNaifBlock

    /**
     * Blocking solution
     *
     * @param id
     * @return
     */
    @GetMapping("/block/{id}")
    fun getShellBlockById(
        @PathVariable id: Long?
    ): SeaShellDto? {
        return seaShellService.getSeaShellNaifBlock(id)
    }

    @get:GetMapping("/reactiveblock")
    val allShellsReactiveBlock: ParallelFlux<SeaShellDto?>?
        /**
         * Reactive Block solution
         *
         * @return
         */
        get() = seaShellService.allSeaShellsReactiveBlock

    @get:GetMapping("/reactiveWithDelay")
    val allShellsReactiveWithDelay: Flux<SeaShellDto?>?
        /**
         * Reactive with delay
         *
         * @return
         */
        get() = seaShellService.allSeaShellsReactiveWithDelay

    @get:GetMapping("/reactiveWithForkJoins")
    val allShellsReactiveWithForkJoins: Flux<SeaShellDto?>?
        /**
         * Reactive with delay
         *
         * @return
         */
        get() = seaShellService.allSeaShellsReactiveWithForkJoins
}