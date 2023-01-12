package org.jesperancinha.shell.webflux.rest

import org.jesperancinha.shell.webflux.data.*
import org.jesperancinha.shell.webflux.service.SeaShellReactiveService
import org.springframework.data.util.Pair
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.ParallelFlux
import reactor.core.scheduler.Schedulers
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping("/seashells/reactive")
class SeaShellReactiveController(private val seaShellReactiveService: SeaShellReactiveService) {
    @GetMapping
    fun allSeaShells(): ParallelFlux<SeaShellDto> = seaShellReactiveService.allSeaShells()

    @GetMapping("/{id}")
    fun getShell(
        @PathVariable id: Long
    ): Mono<SeaShellDto> {
        return seaShellReactiveService.getShell(id)
    }

    @GetMapping("/rootShell/{idPerson}/{idCostume}")
    fun getRootShell(
        @PathVariable idPerson: Long,
        @PathVariable idCostume: Long
    ): Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> {
        return seaShellReactiveService.getRootShell(idPerson, idCostume)
    }

    @GetMapping("/rootCostume/{idTop}/{idLower}")
    fun getRootCostume(
        @PathVariable idTop: Long,
        @PathVariable idLower: Long
    ): Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> {
        return seaShellReactiveService.getRootCostume(idTop, idLower)
    }

    @GetMapping("/rootCostumeSlowTop/{idTop}/{idLower}")
    fun getRootCostumeSlowTop(
        @PathVariable idTop: Long?,
        @PathVariable idLower: Long?
    ): Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> {
        val atomicInteger = AtomicInteger(0)
        return getPairMono(atomicInteger, 4000, 3000)
    }

    @GetMapping("/rootCostumeSlowLower/{idTop}/{idLower}")
    fun getRootCostumeSlowLower(
        @PathVariable idTop: Long?,
        @PathVariable idLower: Long?
    ): Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> {
        val atomicInteger = AtomicInteger(0)
        return getPairMono(atomicInteger, 3000, 4000)
    }

    private fun getPairMono(
        atomicInteger: AtomicInteger,
        sleepTop: Int,
        sleeLower: Int
    ): Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> {
        val seaShellDtoMono = Mono.fromCallable {
            val seaShellTopDto = SeaShellTopDto()
            Thread.sleep(sleepTop.toLong())
            seaShellTopDto.copy(size = atomicInteger.get().toString())
                .also { atomicInteger.addAndGet(10) }
        }
        val seaShellLowerDtoMono = Mono.fromCallable {
            val seaShellDto = SeaShellLowerDto()
            Thread.sleep(sleeLower.toLong())
            seaShellDto.copy(size = atomicInteger.get().toString())
                .also { atomicInteger.addAndGet(10) }
        }
        return Mono.zip<SeaShellTopDto, SeaShellLowerDto, Pair<SeaShellTopDto, SeaShellLowerDto>>(
            seaShellDtoMono.subscribeOn(Schedulers.parallel()), seaShellLowerDtoMono.subscribeOn(Schedulers.parallel())
        ) { top, lower -> Pair.of(top, lower) }
            .subscribeOn(Schedulers.parallel())
    }
}