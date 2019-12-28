package org.jesperancinha.shell.webflux.service;

import lombok.extern.slf4j.Slf4j;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SeaShellService extends SeaShellConsumerService {

    private final ShellRepository shellRepository;

    public SeaShellService(ShellRepository shellRepository,
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
                .map(SeaShellConverter::toShellDto);
//                .doOnNext(consumerPersons())
//                .doOnNext(consumerCostumes());
    }

    public ParallelFlux<SeaShellDto> findAllSeaShells() {
        return shellRepository
                .findAllSeaShells()
                .map(SeaShellConverter::toShellDto)
                .doOnNext(consumerPersons())
                .doOnNext(consumerCostumes());
    }

    public List<SeaShellDto> findAllSeaShellsBlock() {
        return shellRepository.findAllSeaShellsBlock()
                .parallelStream()
                .map(SeaShellConverter::toShellDto)
                .peek(this::setMainRootElements)
                .collect(Collectors.toList());
    }
}
