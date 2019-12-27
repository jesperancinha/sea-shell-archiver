package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;

import java.util.function.Consumer;

import static org.jesperancinha.shell.webflux.service.SeaShellConverter.toAccountDto;
import static org.jesperancinha.shell.webflux.service.SeaShellConverter.toLowerDto;
import static org.jesperancinha.shell.webflux.service.SeaShellConverter.toShellCostumeDto;
import static org.jesperancinha.shell.webflux.service.SeaShellConverter.toShellPersonDto;
import static org.jesperancinha.shell.webflux.service.SeaShellConverter.toTopDto;

public class SeaShellConsumerService {

    private final ShellCostumeRepository costumeRepository;

    private final ShellPersonRepository personRepository;

    private final ShellAccountRepository accountRepository;

    private final ShellTopRepository topRepository;

    private final ShellLowerRepository lowerRepository;

    public SeaShellConsumerService(ShellCostumeRepository costumeRepository,
                                   ShellPersonRepository personRepository,
                                   ShellAccountRepository accountRepository,
                                   ShellTopRepository topRepository,
                                   ShellLowerRepository lowerRepository) {
        this.costumeRepository = costumeRepository;
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.topRepository = topRepository;
        this.lowerRepository = lowerRepository;
    }

    protected Consumer<SeaShellDto> consumerCostumeContents() {
        return seaShellDto -> seaShellDto.getCostumes().forEach(
                seaShellCostumeDto -> {
                    topRepository.findTopById(seaShellCostumeDto.topId())
                            .subscribe(top -> seaShellCostumeDto.setTopDto(toTopDto(top)));
                    lowerRepository.findLowerById(seaShellCostumeDto.lowerId())
                            .subscribe(lower -> seaShellCostumeDto.setLowerDto(toLowerDto(lower)));
                }
        );
    }

    protected Consumer<SeaShellDto> consumerAccount() {
        return seaShellDto -> seaShellDto.getPersons().forEach(
                seaShellPersonDto -> {
                    accountRepository.findAccountById(seaShellPersonDto.accountId())
                            .subscribe(account -> seaShellPersonDto.setAccountDto(toAccountDto(account)));
                });
    }

    protected Consumer<SeaShellDto> consumerPerson() {
        return seaShellDto -> personRepository
                .findPersons(seaShellDto.personIds())
                .subscribe(person -> seaShellDto
                        .getPersons()
                        .add(toShellPersonDto(person)));
    }

    protected Consumer<SeaShellDto> consumerCostume() {
        return seaShellDto -> costumeRepository
                .findCostumes(seaShellDto.costumeIds())
                .subscribe(costume -> seaShellDto
                        .getCostumes()
                        .add(toShellCostumeDto(costume)));
    }

}
