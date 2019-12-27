package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;

import java.util.function.Consumer;

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

    protected Consumer<SeaShellDto> consumerPersons() {
        return seaShellDto -> personRepository
                .findPersons(seaShellDto.getPersonIds())
                .map(SeaShellConverter::toShellPersonDto)
                .doOnNext(personDto -> seaShellDto
                        .getPersons()
                        .add(personDto))
                .doOnNext(seaShellPersonDto -> costumeRepository.findCostumeById(seaShellPersonDto.getCostumeId())
                        .subscribe(costume -> seaShellPersonDto.setCostumeDto(SeaShellConverter.toShellCostumeDto(costume))))
                .doOnNext(seaShellPersonDto -> accountRepository.findAccountById(seaShellPersonDto.getAccountId())
                        .subscribe(account -> seaShellPersonDto.setAccountDto(SeaShellConverter.toAccountDto(account))))
                .map(SeaShellPersonDto::getCostumeDto)
                .subscribe(consumerCostume());

    }

    protected Consumer<SeaShellDto> consumerCostumes() {
        return seaShellDto -> costumeRepository
                .findCostumes(seaShellDto.getCostumeIds())
                .map(SeaShellConverter::toShellCostumeDto)
                .doOnNext(seaShellCostumeDto -> seaShellDto
                        .getCostumes()
                        .add(seaShellCostumeDto))
                .subscribe(consumerCostume());

    }

    private Consumer<SeaShellCostumeDto> consumerCostume() {
        return seaShellCostumeDto -> {
            topRepository.findTopById(seaShellCostumeDto.getTopId())
                    .subscribe(top -> seaShellCostumeDto.setTopDto(SeaShellConverter.toTopDto(top)));
            lowerRepository.findLowerById(seaShellCostumeDto.getLowerId())
                    .subscribe(lower -> seaShellCostumeDto.setLowerDto(SeaShellConverter.toLowerDto(lower)));
        };
    }

}
