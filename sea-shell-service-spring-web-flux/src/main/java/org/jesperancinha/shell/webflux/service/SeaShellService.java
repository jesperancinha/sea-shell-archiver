package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

@Service
public class SeaShellService {

    private ShellRepository shellRepository;

    public SeaShellService(ShellRepository shellRepository) {
        this.shellRepository = shellRepository;
    }

//    public Flux<SeaShell> findAllCompleteSeaShells() {
//        return null;
//    }
//
//    private List<SeaShellLocation> getSeaShellLocations(List<Long> seaShellLocationListIds) {
//        return shellRepository.findAllLocations(seaShellLocationListIds);
//    }

    public Mono<SeaShellDto> findSeaShellById(Long id) {
        return shellRepository.findSeaShellById(id).map(shell -> SeaShellDto.builder()
                .name(shell.getName())
                .scientificName(shell.getScientificName())
                .slogan(shell.getSlogan())
                .build());
    }

    public ParallelFlux<SeaShellDto> findAllSeaShells() {
        return shellRepository.findAllSeaShells().map(shell -> SeaShellDto.builder()
                .name(shell.getName())
                .scientificName(shell.getScientificName())
                .slogan(shell.getSlogan())
                .build());
    }
//
//    public Mono<SeaShell> updateSeaShell(SeaShell seaShell) {
//        return shellRepository.updateSeaShell(seaShell);
//    }
}
