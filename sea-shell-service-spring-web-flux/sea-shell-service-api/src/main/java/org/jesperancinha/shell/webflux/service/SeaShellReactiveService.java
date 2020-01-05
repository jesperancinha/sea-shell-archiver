package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

public interface SeaShellReactiveService {

    ParallelFlux<SeaShellDto> getAllSeaShells();

    Mono<SeaShellDto> getShell(Long id);

    Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> getRootShell(Long idPerson, Long idCostume);

    Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostume(Long idTop, Long idLower);
}
