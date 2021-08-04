package org.jesperancinha.shell.webflux.immutable.service;

import org.jesperancinha.shell.webflux.immutable.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.immutable.repository.ShellLowerRepositoryImpl;
import org.jesperancinha.shell.webflux.immutable.repository.ShellTopRepositoryImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jofisaes on 04/08/2021
 */
@Service
public class SeaShellsReactiveImmutableService {

    private final ShellTopRepositoryImpl shellTopRepository;
    private final ShellLowerRepositoryImpl shellLowerRepository;

    public SeaShellsReactiveImmutableService(
            ShellTopRepositoryImpl shellTopRepository,
            ShellLowerRepositoryImpl shellLowerRepository
    ) {
        this.shellTopRepository = shellTopRepository;
        this.shellLowerRepository = shellLowerRepository;
    }

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
        return shellTopRepository.findTopById(id)
                .map(SeaShellTopDto::create);
    }

    public Mono<SeaShellLowerDto> getLowerById(Long id) {
        return shellLowerRepository.findLowerById(id)
                .map(SeaShellLowerDto::create);

    }
}
