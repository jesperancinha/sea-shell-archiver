package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.service.SeaShellReactiveOneServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/seashells/one")
public class SeaShellReactiveReactiveOneControllerImpl {

    private final SeaShellReactiveOneServiceImpl seaShellReactiveOneService;

    public SeaShellReactiveReactiveOneControllerImpl(SeaShellReactiveOneServiceImpl seaShellReactiveOneService) {
        this.seaShellReactiveOneService = seaShellReactiveOneService;
    }

    @org.springframework.web.bind.annotation.GetMapping
    public Flux<Long> getAllShells() {
        return seaShellReactiveOneService.getAllIds();
    }

    @org.springframework.web.bind.annotation.GetMapping("/{id}")
    public Mono<SeaShellDto> getShellById(
            @org.springframework.web.bind.annotation.PathVariable Long id) {
        return seaShellReactiveOneService.getSeaShellById(id);
    }

    @org.springframework.web.bind.annotation.GetMapping("/person/{id}")
    public Mono<SeaShellPersonDto> getPersonById(
            @org.springframework.web.bind.annotation.PathVariable Long id) {
        return seaShellReactiveOneService.getPersonById(id);
    }

    @org.springframework.web.bind.annotation.GetMapping("/costume/{id}")
    public Mono<SeaShellCostumeDto> getCostumeById(
            @org.springframework.web.bind.annotation.PathVariable Long id) {
        return seaShellReactiveOneService.getCostumeById(id);
    }

    @org.springframework.web.bind.annotation.GetMapping("/account/{id}")
    public Mono<SeaShellAccountDto> getAccountById(
            @org.springframework.web.bind.annotation.PathVariable String id) {
        return seaShellReactiveOneService.getAccountById(id);
    }

    @org.springframework.web.bind.annotation.GetMapping("/top/{id}")
    public Mono<SeaShellTopDto> getTopById(
            @org.springframework.web.bind.annotation.PathVariable Long id) {
        return seaShellReactiveOneService.getTopById(id);
    }

    @org.springframework.web.bind.annotation.GetMapping("/lower/{id}")
    public Mono<SeaShellLowerDto> getLowerById(
            @org.springframework.web.bind.annotation.PathVariable Long id) {
        return seaShellReactiveOneService.getLowerById(id);
    }
}
