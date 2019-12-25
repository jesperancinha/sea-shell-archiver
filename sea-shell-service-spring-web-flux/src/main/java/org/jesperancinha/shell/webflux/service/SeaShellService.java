package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.model.SeaShell;
import org.jesperancinha.shell.webflux.model.SeaShellLocation;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SeaShellService {

    private ShellRepository shellRepository;

    public SeaShellService(ShellRepository shellRepository) {
        this.shellRepository = shellRepository;
    }

    public Flux<SeaShell> findAllCompleteSeaShells() {
        return null;
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
