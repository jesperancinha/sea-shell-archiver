package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.service.SeaShellServiceSeaShell;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

@RestController
@RequestMapping("/seashells")
public class SeaShellController {
    private SeaShellServiceSeaShell seaShellService;

    public SeaShellController(SeaShellServiceSeaShell seaShellService) {
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
//
//    @PostMapping("/update")
//    private Mono<SeaShell> updateShell(@RequestBody SeaShell seaShell) {
//        return seaShellService.updateSeaShell(seaShell);
//    }

}
