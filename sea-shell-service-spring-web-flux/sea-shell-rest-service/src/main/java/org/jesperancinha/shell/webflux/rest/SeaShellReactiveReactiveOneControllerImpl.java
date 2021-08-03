package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.service.SeaShellReactiveOneService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/seashells/one")
public class SeaShellReactiveReactiveOneControllerImpl implements SeaShellReactiveOneController {

    private final SeaShellReactiveOneService seaShellReactiveOneService;

    public SeaShellReactiveReactiveOneControllerImpl(SeaShellReactiveOneService seaShellReactiveOneService) {
        this.seaShellReactiveOneService = seaShellReactiveOneService;
    }

    public Flux<Long> getAllShells() {
        return seaShellReactiveOneService.getAllIds();
    }

    public Mono<SeaShellDto> getShellById(
            @PathVariable
                    Long id) {
        return seaShellReactiveOneService.getSeaShellById(id);
    }

    public Mono<SeaShellPersonDto> getPersonById(
            @PathVariable
                    Long id) {
        return seaShellReactiveOneService.getPersonById(id);
    }

    public Mono<SeaShellCostumeDto> getCostumeById(
            @PathVariable
                    Long id) {
        return seaShellReactiveOneService.getCostumeById(id);
    }

    public Mono<SeaShellAccountDto> getAccountById(
            @PathVariable
                    String id) {
        return seaShellReactiveOneService.getAccountById(id);
    }

    public Mono<SeaShellTopDto> getTopById(
            @PathVariable
                    Long id) {
        return seaShellReactiveOneService.getTopById(id);
    }

    public Mono<SeaShellLowerDto> getLowerById(
            @PathVariable
                    Long id) {
        return seaShellReactiveOneService.getLowerById(id);
    }
}
