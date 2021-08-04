package org.jesperancinha.shell.webflux.immutable.service;

import org.jesperancinha.shell.webflux.immutable.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellTopDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jofisaes on 04/08/2021
 */
public class SeaShellsReactiveImmutableService {

    public Flux<Long> getAllIds() {
        return Flux.empty();
    }

    public Mono<SeaShellDto> getSeaShellById(Long id) {
        return Mono.empty();
    }

    public Mono<SeaShellPersonDto> getPersonById(Long id) {
        return Mono.empty();
    }

    public Mono<SeaShellCostumeDto> getCostumeById(Long id) {
        return Mono.empty();
    }

    public Mono<SeaShellAccountDto> getAccountById(String id) {
        return Mono.empty();
    }

    public Mono<SeaShellTopDto> getTopById(Long id) {
        return Mono.empty();
    }

    public Mono<SeaShellLowerDto> getLowerById(Long id) {
        return Mono.empty();

    }
}
