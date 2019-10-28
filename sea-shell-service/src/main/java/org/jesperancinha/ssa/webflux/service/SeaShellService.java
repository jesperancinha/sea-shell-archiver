package org.jesperancinha.ssa.webflux.service;

import org.jesperancinha.ssa.webflux.model.SeaShell;
import org.jesperancinha.ssa.webflux.model.SeaShellLocation;
import org.jesperancinha.ssa.webflux.repo.ShellRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.jesperancinha.ssa.webflux.repo.ShellRepository.SEA_SHELL_MAP;

@Service
public class SeaShellService {

    private ShellRepository shellRepository;

    public SeaShellService(ShellRepository shellRepository) {
        this.shellRepository = shellRepository;
    }

    public Flux<SeaShell> findAllCompleteSeaShells() {
        return Flux.fromStream(SEA_SHELL_MAP.values().parallelStream()).doOnNext(seaShell -> seaShell.setSeaShellLocations(getSeaShellLocations(seaShell.getSeaShellLocationListIds())));
    }

    private List<SeaShellLocation> getSeaShellLocations(List<Long> seaShellLocationListIds) {
        return shellRepository.findAllLocations(seaShellLocationListIds);
    }

    public Mono<SeaShell> findSeaShellById(Long id) {
        return shellRepository.findSeaShellById(id);
    }

    public Flux<SeaShell> findAllSeaShells() {
        return shellRepository.findAllSeaShells();
    }

    public Mono<SeaShell> updateSeaShell(SeaShell seaShell) {
        return shellRepository.updateSeaShell(seaShell);
    }
}
