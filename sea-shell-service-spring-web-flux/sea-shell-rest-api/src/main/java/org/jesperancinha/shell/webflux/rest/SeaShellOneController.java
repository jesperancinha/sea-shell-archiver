package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SeaShellOneController {
    @GetMapping
    Flux<Long> getAllShells();

    @GetMapping("/{id}")
    Mono<SeaShellDto> getShellById(@PathVariable Long id);

    @GetMapping("/person/{id}")
    Mono<SeaShellPersonDto> getPersonById(@PathVariable Long id);

    @GetMapping("/costume/{id}")
    Mono<SeaShellCostumeDto> getCostumeById(@PathVariable Long id);

    @GetMapping("/account/{id}")
    Mono<SeaShellAccountDto> getAccountById(@PathVariable String id);

    @GetMapping("/top/{id}")
    Mono<SeaShellTopDto> getTopById(@PathVariable Long id);

    @GetMapping("/lower/{id}")
    Mono<SeaShellLowerDto> getLowerById(@PathVariable Long id);
}
