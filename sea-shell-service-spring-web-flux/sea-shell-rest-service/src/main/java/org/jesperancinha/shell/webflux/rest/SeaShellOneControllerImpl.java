package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.service.SeaShellOneService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/seashells/one")
public class SeaShellOneControllerImpl implements SeaShellOneController {

    private final SeaShellOneService seaShellOneService;

    public SeaShellOneControllerImpl(SeaShellOneService seaShellOneService) {
        this.seaShellOneService = seaShellOneService;
    }

    public Flux<Long> getAllShells() {
        return seaShellOneService.findAllIds();
    }

    public Mono<SeaShellDto> getShellById(@PathVariable Long id) {
        return seaShellOneService.getSeaShellById(id);
    }

    public Mono<SeaShellPersonDto> getPersonById(@PathVariable Long id) {
        return seaShellOneService.getPersonById(id);
    }

    public Mono<SeaShellCostumeDto> getCostumeById(@PathVariable Long id) {
        return seaShellOneService.getCostumeById(id);
    }

    public Mono<SeaShellAccountDto> getAccountById(@PathVariable String id) {
        return seaShellOneService.getAccountById(id);
    }

    public Mono<SeaShellTopDto> getTopById(@PathVariable Long id) {
        return seaShellOneService.getTopById(id);
    }

    public Mono<SeaShellLowerDto> getLowerById(@PathVariable Long id) {
        return seaShellOneService.getLowerById(id);
    }
}
