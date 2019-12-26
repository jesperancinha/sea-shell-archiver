package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.service.SeaShellService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

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
//
//    @GetMapping("/completed")
//    private Flux<SeaShell> getAllCompleteShells() {
//        return seaShellService.findAllCompleteSeaShells();
//    }
//
//    @PostMapping("/update")
//    private Mono<SeaShell> updateShell(@RequestBody SeaShell seaShell) {
//        return seaShellService.updateSeaShell(seaShell);
//    }

}
