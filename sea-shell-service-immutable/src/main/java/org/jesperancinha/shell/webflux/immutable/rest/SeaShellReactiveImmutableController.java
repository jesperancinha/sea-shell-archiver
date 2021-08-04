package org.jesperancinha.shell.webflux.immutable.rest;


import org.jesperancinha.shell.webflux.immutable.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.immutable.service.SeaShellsReactiveImmutableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/seashells/immutable")
public class SeaShellReactiveImmutableController {

    private final SeaShellsReactiveImmutableService seaShellsReactiveImmutableService;

    public SeaShellReactiveImmutableController(SeaShellsReactiveImmutableService seaShellsReactiveImmutableService) {
        this.seaShellsReactiveImmutableService = seaShellsReactiveImmutableService;
    }

    @GetMapping
    public Flux<Long> getAllShells() {
        return seaShellsReactiveImmutableService.getAllIds();
    }

    @GetMapping("/{id}")
    public Mono<SeaShellDto> getShellById(
            @PathVariable
                    Long id) {
        return seaShellsReactiveImmutableService.getSeaShellById(id);
    }

    @GetMapping("/person/{id}")
    public Mono<SeaShellPersonDto> getPersonById(
            @PathVariable
                    Long id) {
        return seaShellsReactiveImmutableService.getPersonById(id);
    }

    @GetMapping("/costume/{id}")
    public Mono<SeaShellCostumeDto> getCostumeById(
            @PathVariable
                    Long id) {
        return seaShellsReactiveImmutableService.getCostumeById(id);
    }

    @GetMapping("/account/{id}")
    public Mono<SeaShellAccountDto> getAccountById(
            @PathVariable
                    String id) {
        return seaShellsReactiveImmutableService.getAccountById(id);
    }

    @GetMapping("/top/{id}")
    public Mono<SeaShellTopDto> getTopById(
            @PathVariable
                    Long id) {
        return seaShellsReactiveImmutableService.getTopById(id);
    }

    @GetMapping("/lower/{id}")
    public Mono<SeaShellLowerDto> getLowerById(
            @PathVariable
                    Long id) {
        return seaShellsReactiveImmutableService.getLowerById(id);
    }
}
