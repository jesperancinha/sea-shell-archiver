package org.jesperancinha.shell.webflux.rest;

import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.service.SeaShellOneService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/seashells/one")
public class SeaShellOneController {

    private final SeaShellOneService seaShellOneService;

    public SeaShellOneController(SeaShellOneService seaShellOneService) {
        this.seaShellOneService = seaShellOneService;
    }

    @GetMapping("/{id}")
    private Mono<SeaShellDto> getShellById(@PathVariable Long id) {
        return seaShellOneService.findSeaShellById(id);
    }

    @GetMapping("/person/{id}")
    private Mono<SeaShellPersonDto> getPersonById(@PathVariable Long id) {
        return seaShellOneService.findPersonById(id);
    }

    @GetMapping("/costume/{id}")
    private Mono<SeaShellCostumeDto> getCostumeById(@PathVariable Long id) {
        return seaShellOneService.findCostumeById(id);
    }


    @GetMapping("/rootShell/{idPerson}/{idCostume}")
    private Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> getRootShell(
            @PathVariable Long idPerson, @PathVariable Long idCostume) {
        return seaShellOneService.findPersonById(idPerson)
                .zipWith(seaShellOneService.findCostumeById(idCostume), Pair::of);
    }

    @GetMapping("/account/{id}")
    private Mono<SeaShellAccountDto> getAccountById(@PathVariable String id) {
        return seaShellOneService.findAccountById(id);
    }

    @GetMapping("/top/{id}")
    private Mono<SeaShellTopDto> getTopById(@PathVariable Long id) {
        return seaShellOneService.findTopById(id);
    }

    @GetMapping("/lower/{id}")
    private Mono<SeaShellLowerDto> getLowerById(@PathVariable Long id) {
        return seaShellOneService.findLowerById(id);
    }

    @GetMapping("/rootCostume/{idTop}/{idLower}")
    private Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostume(
            @PathVariable Long idTop, @PathVariable Long idLower) {
        return seaShellOneService.findTopById(idTop)
                .zipWith(seaShellOneService.findLowerById(idLower), Pair::of);

    }
}
