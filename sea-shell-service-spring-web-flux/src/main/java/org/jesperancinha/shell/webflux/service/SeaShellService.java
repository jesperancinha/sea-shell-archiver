package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.client.accounts.Account;
import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.client.lowers.Lower;
import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.client.tops.Top;
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
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.ArrayList;
import java.util.function.Consumer;

@Service
public class SeaShellService {

    private final ShellRepository shellRepository;

    private final ShellCostumeRepository costumeRepository;

    private final ShellPersonRepository personRepository;

    private final ShellAccountRepository accountRepository;

    private final ShellTopRepository topRepository;

    private final ShellLowerRepository lowerRepository;

    public SeaShellService(ShellRepository shellRepository,
                           ShellCostumeRepository costumeRepository,
                           ShellPersonRepository personRepository,
                           ShellAccountRepository accountRepository,
                           ShellTopRepository topRepository,
                           ShellLowerRepository lowerRepository) {
        this.shellRepository = shellRepository;
        this.costumeRepository = costumeRepository;
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.topRepository = topRepository;
        this.lowerRepository = lowerRepository;
    }

    public Mono<SeaShellDto> findSeaShellById(Long id) {
        return shellRepository.findSeaShellById(id).map(this::toShellDto);
    }

    public ParallelFlux<SeaShellDto> findAllSeaShells() {
        return shellRepository
                .findAllSeaShells()
                .map(this::toShellDto)
                .doOnNext(consumerCostume())
                .doOnNext(consumerPerson())
                .doOnNext(consumerAccount())
                .doOnNext(consumerCostumeContents());
    }

    private Consumer<SeaShellDto> consumerCostumeContents() {
        return seaShellDto -> seaShellDto.getCostumes().forEach(
                seaShellCostumeDto -> {
                    topRepository.findTopById(seaShellCostumeDto.topId())
                            .subscribe(top -> seaShellCostumeDto.setTopDto(toTopDto(top)));
                    lowerRepository.findLowerById(seaShellCostumeDto.lowerId())
                            .subscribe(lower -> seaShellCostumeDto.setLowerDto(toLowerDto(lower)));
                }
        );
    }

    private Consumer<SeaShellDto> consumerAccount() {
        return seaShellDto -> seaShellDto.getPersons().forEach(
                seaShellPersonDto -> {
                    accountRepository.findAccountById(seaShellPersonDto.accountId())
                            .subscribe(account -> seaShellPersonDto.setAccountDto(toAccountDto(account)));
                });
    }

    private Consumer<SeaShellDto> consumerPerson() {
        return seaShellDto -> personRepository
                .findPersons(seaShellDto.personIds())
                .subscribe(person -> seaShellDto
                        .getPersons()
                        .add(toShellPersonDto(person)));
    }

    private Consumer<SeaShellDto> consumerCostume() {
        return seaShellDto -> costumeRepository
                .findCostumes(seaShellDto.costumeIds())
                .subscribe(costume -> seaShellDto
                        .getCostumes()
                        .add(toShellCostumeDto(costume)));
    }

    private SeaShellLowerDto toLowerDto(Lower lower) {
        return SeaShellLowerDto.builder()
                .color(lower.getColor())
                .size(lower.getSize())
                .type(lower.getType())
                .build();
    }

    private SeaShellTopDto toTopDto(Top top) {
        return SeaShellTopDto.builder()
                .color(top.getColor())
                .size(top.getSize())
                .type(top.getType())
                .build();
    }

    private SeaShellAccountDto toAccountDto(Account accountById) {
        return SeaShellAccountDto.builder()
                .currency(accountById.getCurrency())
                .value(accountById.getValue())
                .build();
    }

    private SeaShellPersonDto toShellPersonDto(Person person) {
        return SeaShellPersonDto.builder()
                .name(person.getName())
                .accountDto(new SeaShellAccountDto())
                .activity(person.getActivity())
                .costumeId(person.getCostumeId())
                .accountId(person.getAccountId())
                .build();
    }

    private SeaShellCostumeDto toShellCostumeDto(final Costume costume) {
        return SeaShellCostumeDto.builder()
                .topDto(new SeaShellTopDto())
                .lowerDto(new SeaShellLowerDto())
                .topId(costume.getTopId())
                .lowerId(costume.getLowerId())
                .build();
    }

    private SeaShellDto toShellDto(Shell shell) {
        return SeaShellDto.builder()
                .name(shell.getName())
                .scientificName(shell.getScientificName())
                .slogan(shell.getSlogan())
                .personIds(shell.getPersons())
                .costumeIds(shell.getCostumes())
                .costumes(new ArrayList<>())
                .persons(new ArrayList<>())
                .build();
    }
//
//    public Mono<SeaShell> updateSeaShell(SeaShell seaShell) {
//        return shellRepository.updateSeaShell(seaShell);
//    }
}
