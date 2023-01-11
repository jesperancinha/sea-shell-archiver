package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

public interface SeaShellReactiveController {

    @GetMapping
    ParallelFlux<SeaShellDto> getAllSeaShells();

    @GetMapping("/{id}")
    Mono<SeaShellDto> getShell(
            @PathVariable Long id);

    @GetMapping("/rootCostume/{idTop}/{idLower}")
    Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostume(
            @PathVariable Long idTop, @PathVariable Long idLower);

    @GetMapping("/rootShell/{idPerson}/{idCostume}")
    Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> getRootShell(
            @PathVariable Long idPerson, @PathVariable Long idCostume);

    @GetMapping("/rootCostumeSlowTop/{idTop}/{idLower}")
    Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostumeSlowTop(
            @PathVariable Long idTop, @PathVariable Long idLower);

    @GetMapping("/rootCostumeSlowLower/{idTop}/{idLower}")
    Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostumeSlowLower(
            @PathVariable Long idTop, @PathVariable Long idLower);
}
