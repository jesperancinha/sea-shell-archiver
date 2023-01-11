package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.service.SeaShellServiceImpl;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

@RestController
@RequestMapping("/seashells")
public class SeaShellControllerImpl {

    private final SeaShellServiceImpl seaShellService;

    public SeaShellControllerImpl(SeaShellServiceImpl seaShellService) {
        this.seaShellService = seaShellService;
    }

    @org.springframework.web.bind.annotation.GetMapping(path = "/{id}")
    public Mono<SeaShellDto> getShellById(@PathVariable Long id) {
        return seaShellService.getSeaShellById(id).mapNotNull(
                seaShellDto -> ResponseEntity.ok(seaShellDto).getBody());
    }

    @org.springframework.web.bind.annotation.GetMapping
    public ParallelFlux<SeaShellDto> getAllShells() {
        return seaShellService.getAllSeaShells();
    }

    /**
     * Just the slogans
     *
     * @return
     */
    @org.springframework.web.bind.annotation.GetMapping("/slogans")
    public ParallelFlux<Pair<String, String>> getShellSlogans() {
        return seaShellService.getAllSeaShells().map(seaShellDto -> Pair.of(seaShellDto.name(), seaShellDto.slogan()));
    }

    /**
     * Blocking solution
     *
     * @return
     */
    @org.springframework.web.bind.annotation.GetMapping("/block")
    public List<SeaShellDto> getAllShellsBlock() {
        return seaShellService.getAllSeaShellsNaifBlock();
    }

    /**
     * Blocking solution
     *
     * @param id
     * @return
     */
    @org.springframework.web.bind.annotation.GetMapping("/block/{id}")
    public SeaShellDto getShellBlockById(
            @PathVariable
                    Long id) {
        return seaShellService.getSeaShellNaifBlock(id);
    }

    /**
     * Reactive Block solution
     *
     * @return
     */
    @org.springframework.web.bind.annotation.GetMapping("/reactiveblock")
    public ParallelFlux<SeaShellDto> getAllShellsReactiveBlock() {
        return seaShellService.getAllSeaShellsReactiveBlock();
    }

    /**
     * Reactive with delay
     *
     * @return
     */
    @org.springframework.web.bind.annotation.GetMapping("/reactiveWithDelay")
    public Flux<SeaShellDto> getAllShellsReactiveWithDelay() {
        return seaShellService.getAllSeaShellsReactiveWithDelay();
    }

    /**
     * Reactive with delay
     *
     * @return
     */
    @org.springframework.web.bind.annotation.GetMapping("/reactiveWithForkJoins")
    public Flux<SeaShellDto> getAllShellsReactiveWithForkJoins() {
        return seaShellService.getAllSeaShellsReactiveWithForkJoins();
    }
}
