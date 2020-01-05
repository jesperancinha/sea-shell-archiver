package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

public interface SeaShellController {

    @GetMapping("/{id}")
    Mono<SeaShellDto> getShellById(@PathVariable Long id);

    @GetMapping
    ParallelFlux<SeaShellDto> getAllShells();

    @GetMapping("/slogans")
    ParallelFlux<Pair<String, String>> getShellSlogans();

    @GetMapping("/block")
    List<SeaShellDto> getAllShellsBlock();

    @GetMapping("/block/{id}")
    SeaShellDto getShellBlockById(@PathVariable Long id);

    @GetMapping("/reactiveblock")
    ParallelFlux<SeaShellDto> getAllShellsReactiveBlock();

    @GetMapping("/reactiveWithDelay")
    Flux<SeaShellDto> getAllShellsReactiveWithDelay();

    @GetMapping("/reactiveWithForkJoins")
    Flux<SeaShellDto> getAllShellsReactiveWithForkJoins();
}
