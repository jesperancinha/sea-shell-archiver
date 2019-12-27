package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

@Service
public class SeaShellServiceSeaShell extends SeaShellConsumerService {

    private final ShellRepository shellRepository;

    public SeaShellServiceSeaShell(ShellRepository shellRepository,
                                   ShellCostumeRepository costumeRepository,
                                   ShellPersonRepository personRepository,
                                   ShellAccountRepository accountRepository,
                                   ShellTopRepository topRepository,
                                   ShellLowerRepository lowerRepository) {
        super(costumeRepository,
                personRepository,
                accountRepository,
                topRepository,
                lowerRepository);
        this.shellRepository = shellRepository;
    }

    public Mono<SeaShellDto> findSeaShellById(Long id) {
        return shellRepository.findSeaShellById(id)
                .map(SeaShellConverter::toShellDto)
                .doOnNext(consumerCostume())
                .doOnNext(consumerPerson())
                .doOnNext(consumerAccount())
                .doOnNext(consumerCostumeContents());
    }

    public ParallelFlux<SeaShellDto> findAllSeaShells() {
        return shellRepository
                .findAllSeaShells()
                .map(SeaShellConverter::toShellDto)
                .doOnNext(consumerCostume())
                .doOnNext(consumerPerson())
                .doOnNext(consumerAccount())
                .doOnNext(consumerCostumeContents());
    }
}
