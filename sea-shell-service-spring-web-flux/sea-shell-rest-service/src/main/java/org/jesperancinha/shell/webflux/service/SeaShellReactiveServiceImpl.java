package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.client.shells.Shell;
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
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static reactor.core.scheduler.Schedulers.parallel;

@Service
public class SeaShellReactiveServiceImpl extends SeaShellOneAdapter implements SeaShellReactiveService {


    public SeaShellReactiveServiceImpl(ShellRepository shellRepository,
                                       ShellPersonRepository shellPersonRepository,
                                       ShellCostumeRepository shellCostumeRepository,
                                       ShellAccountRepository shellAccountRepository,
                                       ShellTopRepository shellTopRepository,
                                       ShellLowerRepository shellLowerRepository) {
        super(
                shellRepository,
                shellPersonRepository,
                shellCostumeRepository,
                shellAccountRepository,
                shellTopRepository,
                shellLowerRepository
        );
    }

    public Mono<SeaShellDto> getShell(final Long id) {
        return shellRepository.findSeaShellById(id).map(this::getZip).flatMap(Mono::from);
    }

    public Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> getRootShell(Long idPerson, Long idCostume) {
        return Mono.zip(
                shellPersonRepository.findPersonById(idPerson).map(SeaShellConverter::toShellPersonDto).subscribeOn(parallel()),
                shellCostumeRepository.findCostumeById(idCostume).map(SeaShellConverter::toShellCostumeDto).subscribeOn(parallel()),
                Pair::of).subscribeOn(parallel());
    }

    public Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostume(Long idTop, Long idLower) {
        return Mono.zip(
                shellTopRepository.findTopById(idTop).map(SeaShellConverter::toTopDto).subscribeOn(parallel()),
                shellLowerRepository.findLowerById(idLower).map(SeaShellConverter::toLowerDto).subscribeOn(parallel()),
                Pair::of).subscribeOn(parallel());
    }

    private Mono<SeaShellDto> getZip(Shell seaShell) {
        final SeaShellDto seaShellDtoReturn = SeaShellConverter.toShellDto(seaShell);
        return Mono.zip(
                Mono.just(seaShell.getPersons()).subscribeOn(parallel()).map(shellPersonRepository::findPersonsBlock).subscribeOn(parallel()),
                Mono.just(seaShell.getCostumes()).subscribeOn(parallel()).map(shellCostumeRepository::findCostumesBlock).subscribeOn(parallel()),
                (persons, costumes) -> {
                    final SeaShellDto seaShellDto = SeaShellConverter.toShellDto(seaShell);
                    persons.forEach(person -> seaShellDto.getPersons().add(SeaShellConverter.toShellPersonDto(person)));
                    costumes.forEach(costume -> seaShellDto.getCostumes().add(SeaShellConverter.toShellCostumeDto(costume)));
                    return seaShellDto;
                })
                .subscribeOn(Schedulers.parallel())
                .map(seaShellDto -> seaShellDto.getCostumes().parallelStream().map(seaShellCostumeDto -> {
                    seaShellCostumeDto.setTopDto(SeaShellConverter.toTopDto(shellTopRepository.findTopByIdBlock(seaShellCostumeDto.getTopId())));
                    return seaShellDto;
                }).findFirst().orElse(SeaShellDto.builder().build()))
                .map(seaShellDto -> seaShellDto.getCostumes().parallelStream().map(seaShellCostumeDto -> {
                    seaShellCostumeDto.setLowerDto(SeaShellConverter.toLowerDto(shellLowerRepository.findLowerByIdBlock(seaShellCostumeDto.getLowerId())));
                    return seaShellDto;
                }).findFirst().orElse(SeaShellDto.builder().build()));
    }

}
