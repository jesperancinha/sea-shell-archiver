package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.shells.Shell;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

public interface ShellRepository {
    Mono<Shell> findSeaShellById(Long id);

    ParallelFlux<Shell> findAllSeaShells();

    Flux<Long> findAllShellIds();

    List<Shell> findAllSeaShellsBlock();

    Shell findSeaShellBlockById(Long id);
}
