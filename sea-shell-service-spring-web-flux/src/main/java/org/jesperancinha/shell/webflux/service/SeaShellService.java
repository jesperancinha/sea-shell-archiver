package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepository;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.ArrayList;

@Service
public class SeaShellService {

    private final ShellRepository shellRepository;

    private final ShellCostumeRepository costumeRepository;

    private final ShellPersonRepository personRepository;

    public SeaShellService(ShellRepository shellRepository,
                           ShellCostumeRepository costumeRepository,
                           ShellPersonRepository personRepository) {
        this.shellRepository = shellRepository;
        this.costumeRepository = costumeRepository;
        this.personRepository = personRepository;
    }

//    public Flux<SeaShell> findAllCompleteSeaShells() {
//        return null;
//    }
//
//    private List<SeaShellLocation> getSeaShellLocations(List<Long> seaShellLocationListIds) {
//        return shellRepository.findAllLocations(seaShellLocationListIds);
//    }

    public Mono<SeaShellDto> findSeaShellById(Long id) {
        return shellRepository.findSeaShellById(id).map(this::toShellDto);
    }

    public ParallelFlux<SeaShellDto> findAllSeaShells() {
        return shellRepository
                .findAllSeaShells()
                .map(this::toShellDto)
                .doOnNext(seaShellDto -> costumeRepository
                        .findCostumes(seaShellDto.getCostumeIds())
                        .subscribe(costume -> seaShellDto
                                .getCostumes()
                                .add(toShellCostumeDto(costume))))
                .doOnNext(seaShellDto -> personRepository
                        .findPersons(seaShellDto.getPersonIds())
                        .subscribe(person -> seaShellDto
                                .getPersons()
                                .add(toShellPersonDto(person))));
    }

    private SeaShellPersonDto toShellPersonDto(Person person) {
        return SeaShellPersonDto.builder()
                .name(person.getName())
                .accountDto(new SeaShellAccountDto())
                .activity(person.getActivity())
                .build();
    }

    private SeaShellCostumeDto toShellCostumeDto(Costume costume) {
        return SeaShellCostumeDto.builder()
                .topDto(new SeaShellTopDto())
                .lowerDto(new SeaShellLowerDto())
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
