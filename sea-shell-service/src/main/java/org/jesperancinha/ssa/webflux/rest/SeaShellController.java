package org.jesperancinha.ssa.webflux.rest;

import org.jesperancinha.ssa.webflux.model.SeaShell;
import org.jesperancinha.ssa.webflux.repo.ShellRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/seashells")
public class SeaShellController {
    private ShellRepository shellRepository;

    public SeaShellController(ShellRepository shellRepository) {
        this.shellRepository = shellRepository;
    }

    @GetMapping("/{id}")
    private Mono<SeaShell> getShellById(@PathVariable Long id) {
        return shellRepository.findSeaShellById(id);
    }

    @GetMapping
    private Flux<SeaShell> getAllShells() {
        return shellRepository.findAllSeaShells();
    }

    @GetMapping
    private Flux<SeaShell> getAllCompleteShells() {
        return shellRepository.findAllCompleteSeaShells();
    }

    @PostMapping("/update")
    private Mono<SeaShell> updateShell(@RequestBody SeaShell seaShell) {
        return shellRepository.updateSeaShell(seaShell);
    }

}
