package org.jesperancinha.shell.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;

import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class SeaShellConsumerAdapter {

    protected final ShellCostumeRepository costumeRepository;

    protected final ShellPersonRepository personRepository;

    protected final ShellAccountRepository accountRepository;

    protected final ShellTopRepository topRepository;

    protected final ShellLowerRepository lowerRepository;

    public SeaShellConsumerAdapter(ShellCostumeRepository costumeRepository,
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
                .findPersons(seaShellDto.personIds())
                .map(SeaShellConverter::toShellPersonDto)
                .doOnNext(personDto -> seaShellDto
                        .persons()
                        .add(personDto))
                .subscribe(seaShellPersonDto -> {
                    costumeRepository.findCostumeById(seaShellPersonDto.getCostumeId())
                            .subscribe(costume -> {
                                seaShellPersonDto.setCostumeDto(SeaShellConverter.toShellCostumeDto(costume));
                                final SeaShellCostumeDto costumeDto = seaShellPersonDto.getCostumeDto();
                                consumerCostume().accept(costumeDto);
                                log.info("Complete costume before calling sub top/lower threads ->" + costumeDto);
                            });
                    accountRepository.findAccountById(seaShellPersonDto.getAccountId())
                            .subscribe(account -> {
                                seaShellPersonDto.setAccountDto(SeaShellConverter.toAccountDto(account));
                                log.info("Complete account after calling account thread ->" + seaShellPersonDto.getAccountDto());
                            });

                });
    }

    protected Consumer<SeaShellDto> consumerCostumes() {
        return seaShellDto -> costumeRepository
                .findCostumes(seaShellDto.costumeIds())
                .map(SeaShellConverter::toShellCostumeDto)
                .doOnNext(seaShellCostumeDto -> seaShellDto
                        .costumes()
                        .add(seaShellCostumeDto))
                .subscribe(consumerCostume());

    }

    private Consumer<SeaShellCostumeDto> consumerCostume() {
        return seaShellCostumeDto -> {
            topRepository.findTopById(seaShellCostumeDto.getTopId())
                    .subscribe(top -> {
                        seaShellCostumeDto.setTopDto(SeaShellConverter.toTopDto(top));
                        log.info("Complete costume after calling sub top thread ->" + seaShellCostumeDto);
                    });
            lowerRepository.findLowerById(seaShellCostumeDto.getLowerId())
                    .subscribe(lower -> {
                        seaShellCostumeDto.setLowerDto(SeaShellConverter.toLowerDto(lower));
                        log.info("Complete costume after calling sub top thread ->" + seaShellCostumeDto);
                    });
        };
    }

    protected void setMainRootElements(SeaShellDto seaShellDto) {
        seaShellDto.addCostumes(costumeRepository
                .findCostumesBlock(seaShellDto.costumeIds())
                .parallelStream()
                .map(SeaShellConverter::toShellCostumeDto)
                .peek(this::setCostumeRootElements)
                .collect(Collectors.toList()));
        seaShellDto.addPersons(personRepository
                .findPersonsBlock(seaShellDto.personIds())
                .parallelStream()
                .map(SeaShellConverter::toShellPersonDto)
                .peek(seaShellPersonDto -> seaShellPersonDto
                        .setAccountDto(SeaShellConverter
                                .toAccountDto(accountRepository.findAccountByIdBlock(seaShellPersonDto.getAccountId()))))
                .peek(seaShellPersonDto -> seaShellPersonDto
                        .setCostumeDto(SeaShellConverter
                                .toShellCostumeDto(costumeRepository.findCostumeByIdBlock(seaShellPersonDto.getCostumeId()))))
                .peek(seaShellPersonDto -> setCostumeRootElements(seaShellPersonDto.getCostumeDto()))
                .collect(Collectors.toList()));
    }

    private void setCostumeRootElements(SeaShellCostumeDto seaShellCostumeDto) {
        seaShellCostumeDto.setTopDto(SeaShellConverter.toTopDto(topRepository.findTopByIdBlock(seaShellCostumeDto.getTopId())));
        seaShellCostumeDto.setLowerDto(SeaShellConverter.toLowerDto(lowerRepository.findLowerByIdBlock(seaShellCostumeDto.getLowerId())));
    }
}
