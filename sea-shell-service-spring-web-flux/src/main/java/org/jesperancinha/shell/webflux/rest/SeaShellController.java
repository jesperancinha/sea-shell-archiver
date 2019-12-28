package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.service.SeaShellService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private ParallelFlux<SeaShellDto> getAllShells() {
        return seaShellService.findAllSeaShells();
    }

    @GetMapping("/slogans")
    private ParallelFlux<Pair<String, String>> getAllCompleteShells() {
        return seaShellService.findAllSeaShells().map(seaShellDto -> Pair.of(seaShellDto.getName(), seaShellDto.getSlogan()));
    }

    /**
     * Blocking solution
     * @return
     */
    @GetMapping("/block")
    private List<SeaShellDto> getAllShellsBlock() {
        return seaShellService.findAllSeaShellsNaifBlock();
    }

    /**
     * Blocking solution
     * @return
     */
    @GetMapping("/reactiveblock")
    private ParallelFlux<SeaShellDto> getAllShellsReactiveBlock() {
        return seaShellService.findAllSeaShellsReactiveBlock();
    }
}
