package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.service.SeaShellReactiveServiceImpl;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/seashells/reactive")
public class SeaShellReactiveControllerImpl {

    private final SeaShellReactiveServiceImpl seaShellReactiveService;

    public SeaShellReactiveControllerImpl(SeaShellReactiveServiceImpl seaShellReactiveService) {
        this.seaShellReactiveService = seaShellReactiveService;
    }

    @org.springframework.web.bind.annotation.GetMapping
    public ParallelFlux<SeaShellDto> getAllSeaShells() {
        return seaShellReactiveService.getAllSeaShells();
    }

    @org.springframework.web.bind.annotation.GetMapping("/{id}")
    public Mono<SeaShellDto> getShell(
            @PathVariable
                    Long id) {
        return seaShellReactiveService.getShell(id);
    }

    @org.springframework.web.bind.annotation.GetMapping("/rootShell/{idPerson}/{idCostume}")
    public Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> getRootShell(
            @PathVariable
                    Long idPerson,
            @PathVariable
                    Long idCostume) {
        return seaShellReactiveService.getRootShell(idPerson, idCostume);
    }

    @org.springframework.web.bind.annotation.GetMapping("/rootCostume/{idTop}/{idLower}")
    public Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostume(
            @PathVariable
                    Long idTop,
            @PathVariable
                    Long idLower) {
        return seaShellReactiveService.getRootCostume(idTop, idLower);
    }

    @org.springframework.web.bind.annotation.GetMapping("/rootCostumeSlowTop/{idTop}/{idLower}")
    public Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostumeSlowTop(
            @PathVariable
                    Long idTop,
            @PathVariable
                    Long idLower) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        return getPairMono(atomicInteger, 4000, 3000);
    }

    @org.springframework.web.bind.annotation.GetMapping("/rootCostumeSlowLower/{idTop}/{idLower}")
    public Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostumeSlowLower(
            @PathVariable
                    Long idTop,
            @PathVariable
                    Long idLower) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        return getPairMono(atomicInteger, 3000, 4000);
    }


    private Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getPairMono(AtomicInteger atomicInteger, int sleepTop, int sleeLower) {
        final Mono<SeaShellTopDto> seaShellDtoMono = Mono.fromCallable(() -> {
            SeaShellTopDto seaShellTopDto = new SeaShellTopDto();

            Thread.sleep(sleepTop);
            seaShellTopDto.setSize(String.valueOf(atomicInteger.get()));
            atomicInteger.addAndGet(10);
            return seaShellTopDto;
        });
        Mono<SeaShellLowerDto> SeaShellLowerDto = Mono.fromCallable(() -> {
            SeaShellLowerDto seaShellDto = new SeaShellLowerDto();
            Thread.sleep(sleeLower);
            seaShellDto.setSize(String.valueOf(atomicInteger.get()));
            atomicInteger.addAndGet(10);
            return seaShellDto;
        });

        return Mono.zip(seaShellDtoMono.subscribeOn(Schedulers.parallel()), SeaShellLowerDto.subscribeOn(Schedulers.parallel())
                , Pair::of).subscribeOn(Schedulers.parallel());
    }
}
