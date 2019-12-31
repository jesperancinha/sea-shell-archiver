package org.jesperancinha.shell.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.jesperancinha.shell.webflux.repo.ShellRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SeaShellOneServiceImpl implements SeaShellOneService {

    private final ShellRepository shellRepository;

    private final ShellPersonRepository shellPersonRepository;

    private final ShellCostumeRepository shellCostumeRepository;

    private final ShellAccountRepository shellAccountRepository;

    private final ShellTopRepository shellTopRepository;

    private final ShellLowerRepository shellLowerRepository;

    public SeaShellOneServiceImpl(ShellRepository shellRepository,
                                  ShellPersonRepository shellPersonRepository,
                                  ShellCostumeRepository shellCostumeRepository,
                                  ShellAccountRepository shellAccountRepository,
                                  ShellTopRepository shellTopRepository,
                                  ShellLowerRepository shellLowerRepository) {
        this.shellRepository = shellRepository;
        this.shellPersonRepository = shellPersonRepository;
        this.shellCostumeRepository = shellCostumeRepository;
        this.shellAccountRepository = shellAccountRepository;
        this.shellTopRepository = shellTopRepository;
        this.shellLowerRepository = shellLowerRepository;
    }

    public Flux<Long> findAllIds() {
        return this.shellRepository.findAllShellIds();
    }

    public Mono<SeaShellDto> getSeaShellById(Long id) {
        return this.shellRepository.findSeaShellById(id)
                .map(SeaShellConverter::toShellDto);
    }

    public Mono<SeaShellPersonDto> getPersonById(Long id) {
        return this.shellPersonRepository.findPersonById(id)
                .map(SeaShellConverter::toShellPersonDto);
    }

    public Mono<SeaShellCostumeDto> getCostumeById(Long id) {
        return this.shellCostumeRepository.findCostumeById(id)
                .map(SeaShellConverter::toShellCostumeDto);
    }

    public Mono<SeaShellAccountDto> getAccountById(String id) {
        return this.shellAccountRepository.findAccountById(id)
                .map(SeaShellConverter::toAccountDto);
    }

    public Mono<SeaShellTopDto> getTopById(Long id) {
        return this.shellTopRepository.findTopById(id)
                .map(SeaShellConverter::toTopDto);

    }

    public Mono<SeaShellLowerDto> getLowerById(Long id) {
        return this.shellLowerRepository.findLowerById(id)
                .map(SeaShellConverter::toLowerDto);

    }
}
