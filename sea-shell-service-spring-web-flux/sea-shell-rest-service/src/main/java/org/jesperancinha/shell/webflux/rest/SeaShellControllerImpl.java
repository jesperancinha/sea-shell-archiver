package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.service.SeaShellService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

@RestController
@RequestMapping("/seashells")
public class SeaShellControllerImpl implements SeaShellController {

    private SeaShellService seaShellService;

    public SeaShellControllerImpl(SeaShellService seaShellService) {
        this.seaShellService = seaShellService;
    }

    public Mono<SeaShellDto> getShellById(@PathVariable Long id) {
        return seaShellService.getSeaShellById(id);
    }

    public ParallelFlux<SeaShellDto> getAllShells() {
        return seaShellService.getAllSeaShells();
    }

    /**
     * Just the slogans
     *
     * @return
     */
    public ParallelFlux<Pair<String, String>> getShellSlogans() {
        return seaShellService.getAllSeaShells().map(seaShellDto -> Pair.of(seaShellDto.getName(), seaShellDto.getSlogan()));
    }

    /**
     * Blocking solution
     *
     * @return
     */
    public List<SeaShellDto> getAllShellsBlock() {
        return seaShellService.getAllSeaShellsNaifBlock();
    }

    /**
     * Blocking solution
     *
     * @param id
     * @return
     */
    public SeaShellDto getShellBlockById(@PathVariable Long id) {
        return seaShellService.getSeaShellNaifBlock(id);
    }

    /**
     * Reactive Block solution
     *
     * @return
     */
    public ParallelFlux<SeaShellDto> getAllShellsReactiveBlock() {
        return seaShellService.getAllSeaShellsReactiveBlock();
    }

    /**
     * Reactive with delay
     *
     * @return
     */
    public Flux<SeaShellDto> getAllShellsReactiveWithDelay() {
        return seaShellService.getAllSeaShellsReactiveWithDelay();
    }
}
