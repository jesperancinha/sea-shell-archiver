package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.service.SeaShellService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

@RestController
@RequestMapping("/seashells")
public class SeaShellController {
    private SeaShellService seaShellService;

    public SeaShellController(SeaShellService seaShellService) {
        this.seaShellService = seaShellService;
    }

    @GetMapping("/{id}")
    private Mono<SeaShellDto> getShellById(@PathVariable Long id) {
        return seaShellService.findSeaShellById(id);
    }

    @GetMapping
    public ParallelFlux<SeaShellDto> getAllShells() {
        return seaShellService.findAllSeaShells();
    }

    /**
     * Just the slogans
     *
     * @return
     */
    @GetMapping("/slogans")
    public ParallelFlux<Pair<String, String>> getAllCompleteShells() {
        return seaShellService.findAllSeaShells().map(seaShellDto -> Pair.of(seaShellDto.getName(), seaShellDto.getSlogan()));
    }

    /**
     * Blocking solution
     *
     * @return
     */
    @GetMapping("/block")
    public List<SeaShellDto> getAllShellsBlock() {
        return seaShellService.findAllSeaShellsNaifBlock();
    }

    /**
     * Blocking solution
     * @param id
     * @return
     */
    @GetMapping("/block/{id}")
    public SeaShellDto getShellBlockById(@PathVariable Long id) {
        return seaShellService.findAllSeaShellsNaifBlock(id);
    }

    /**
     * Reactive Block solution
     *
     * @return
     */
    @GetMapping("/reactiveblock")
    public ParallelFlux<SeaShellDto> getAllShellsReactiveBlock() {
        return seaShellService.findAllSeaShellsReactiveBlock();
    }

    /**
     * Reactive with delay
     *
     * @return
     */
    @GetMapping("/reactiveWithDelay")
    public Flux<SeaShellDto> getAllShellsReactiveWithDelay() {
        return seaShellService.findAllSeaShellsReactiveWithDelay();
    }
}
