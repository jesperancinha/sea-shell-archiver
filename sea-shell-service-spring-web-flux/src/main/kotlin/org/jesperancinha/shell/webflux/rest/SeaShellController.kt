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
class SeaShellController(private val seaShellService: SeaShellServiceImpl) {
    @GetMapping(path = ["/{id}"])
    fun getShellById(@PathVariable id: Long): Mono<SeaShellDto?> {
        return seaShellService.getSeaShellById(id)
            .mapNotNull { seaShellDto: SeaShellDto? -> ResponseEntity.ok(seaShellDto).body }
    }

    @GetMapping
    fun allShells(): ParallelFlux<SeaShellDto> = seaShellService.allSeaShells()

    @GetMapping("/slogans")
    fun shellSlogans(): ParallelFlux<Pair<String, String>> =
        seaShellService.allSeaShells().map { seaShellDto: SeaShellDto? ->
            Pair.of(
                seaShellDto!!.name, seaShellDto.slogan
            )
        }

    @GetMapping("/block")
    fun allShellsBlock(): List<SeaShellDto> = seaShellService.allSeaShellsNaifBlock()

    /**
     * Blocking solution
     *
     * @param id
     * @return
     */
    @GetMapping("/block/{id}")
    fun getShellBlockById(
        @PathVariable id: Long
    ): SeaShellDto? {
        return seaShellService.getSeaShellNaifBlock(id)
    }

    @GetMapping("/reactiveblock")
    fun allShellsReactiveBlock(): ParallelFlux<SeaShellDto> = seaShellService.allSeaShellsReactiveBlock()

    @GetMapping("/reactiveWithDelay")
    fun allShellsReactiveWithDelay(): Flux<SeaShellDto> = seaShellService.allSeaShellsReactiveWithDelay()

    /**
     * Delay solution
     */
    @GetMapping("/reactiveWithForkJoins")
    fun allShellsReactiveWithForkJoins(): Flux<SeaShellDto> = seaShellService.allSeaShellsReactiveWithForkJoins()
}