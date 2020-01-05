package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SeaShellReactiveOneService {
    Flux<Long> getAllIds();

    Mono<SeaShellDto> getSeaShellById(Long id);

    Mono<SeaShellPersonDto> getPersonById(Long id);

    Mono<SeaShellCostumeDto> getCostumeById(Long id);

    Mono<SeaShellAccountDto> getAccountById(String id);

    Mono<SeaShellTopDto> getTopById(Long id);

    Mono<SeaShellLowerDto> getLowerById(Long id);
}
