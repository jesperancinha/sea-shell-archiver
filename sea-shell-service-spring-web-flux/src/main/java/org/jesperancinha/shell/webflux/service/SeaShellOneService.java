package org.jesperancinha.shell.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class SeaShellOneService {

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    @Value("${sea.shell.delay.ms:100}")
    private Integer delay;

    private final ShellRepository shellRepository;

    private final ShellPersonRepository shellPersonRepository;

    private final ShellCostumeRepository shellCostumeRepository;

    private final ShellAccountRepository shellAccountRepository;

    private final ShellTopRepository shellTopRepository;

    private final ShellLowerRepository shellLowerRepository;

    public SeaShellOneService(ShellRepository shellRepository,
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

    public Mono<SeaShellDto> findSeaShellById(Long id) {
        return this.shellRepository.findSeaShellById(id)
                .map(SeaShellConverter::toShellDto);
    }

    public Mono<SeaShellPersonDto> findPersonById(Long id) {
        return this.shellPersonRepository.findPersonById(id)
                .map(SeaShellConverter::toShellPersonDto);
    }

    public Mono<SeaShellCostumeDto> findCostumeById(Long id) {
        return this.shellCostumeRepository.findCostumeById(id)
                .map(SeaShellConverter::toShellCostumeDto);
    }

    public Mono<SeaShellAccountDto> findAccountById(String id) {
        return this.shellAccountRepository.findAccountById(id)
                .map(SeaShellConverter::toAccountDto);
    }

    public Mono<SeaShellTopDto> findTopById(Long id) {
        return this.shellTopRepository.findTopById(id)
                .map(SeaShellConverter::toTopDto);

    }
    public Mono<SeaShellLowerDto> findLowerById(Long id) {
        return this.shellLowerRepository.findLowerById(id)
                .map(SeaShellConverter::toLowerDto);

    }
}
