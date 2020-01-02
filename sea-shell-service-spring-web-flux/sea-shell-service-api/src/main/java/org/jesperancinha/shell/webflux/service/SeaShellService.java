package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

public interface SeaShellService {
    Mono<SeaShellDto> getSeaShellById(Long id);

    ParallelFlux<SeaShellDto> getAllSeaShells();

    List<SeaShellDto> getAllSeaShellsNaifBlock();

    SeaShellDto getSeaShellNaifBlock(Long id);

    ParallelFlux<SeaShellDto> getAllSeaShellsReactiveBlock();

    Flux<SeaShellDto> getAllSeaShellsReactiveWithDelay();
}
