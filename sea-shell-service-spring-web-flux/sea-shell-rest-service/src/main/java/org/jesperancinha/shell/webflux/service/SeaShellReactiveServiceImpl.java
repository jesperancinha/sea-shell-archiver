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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import static reactor.core.publisher.Mono.from;
import static reactor.core.publisher.Mono.fromCallable;
import static reactor.core.publisher.Mono.just;
import static reactor.core.publisher.Mono.zip;
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

    public ParallelFlux<SeaShellDto> getAllSeaShells() {
        return shellRepository
                .findAllSeaShells()
                .map(this::fetchSeaShellPublisher).flatMap(Flux::from);
    }

    public Mono<SeaShellDto> getShell(final Long id) {
        return shellRepository.findSeaShellById(id).map(this::fetchSeaShellPublisher).flatMap(Mono::from);
    }

    public Mono<Pair<SeaShellPersonDto, SeaShellCostumeDto>> getRootShell(Long idPerson, Long idCostume) {
        return zip(
                shellPersonRepository.findPersonById(idPerson).map(SeaShellConverter::toShellPersonDto).subscribeOn(parallel()),
                shellCostumeRepository.findCostumeById(idCostume).map(SeaShellConverter::toShellCostumeDto).subscribeOn(parallel()),
                Pair::of).subscribeOn(parallel());
    }

    public Mono<Pair<SeaShellTopDto, SeaShellLowerDto>> getRootCostume(Long idTop, Long idLower) {
        return zip(
                shellTopRepository.findTopById(idTop).map(SeaShellConverter::toTopDto).subscribeOn(parallel()),
                shellLowerRepository.findLowerById(idLower).map(SeaShellConverter::toLowerDto).subscribeOn(parallel()),
                Pair::of).subscribeOn(parallel());
    }

    private Mono<SeaShellDto> fetchSeaShellPublisher(Shell seaShell) {
        final SeaShellDto seaShellDtoReturn = SeaShellConverter.toShellDto(seaShell);

        return zip(
                fetchPersonsPublisher(seaShell, seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
                fetchCostumesPublisher(seaShell, seaShellDtoReturn).subscribeOn(Schedulers.parallel()),
                (persons, costumes) -> seaShellDtoReturn);
    }

    private Mono<?> fetchPersonsPublisher(Shell seaShell, SeaShellDto seaShellDtoReturn) {
        return from(from(just(
                seaShell.getPersons()).subscribeOn(parallel()).map(shellPersonRepository::findPersonsBlock).subscribeOn(parallel())
                .map(persons -> {
                    persons.forEach(person -> seaShellDtoReturn.getPersons().add(SeaShellConverter.toShellPersonDto(person)));
                    return seaShellDtoReturn.getPersons();
                }))
                .thenMany(zip(
                        fetchPersonAccountPublisher(seaShellDtoReturn).subscribeOn(parallel()),
                        fetchPersonFullCostumePublisher(seaShellDtoReturn).subscribeOn(parallel()))));

    }

    private Mono<SeaShellDto> fetchPersonFullCostumePublisher(SeaShellDto seaShellDtoReturn) {
        return from(fetchPersonCostumePublisher(seaShellDtoReturn)
                .thenMany(fetchPersonTopLowerPublisher(seaShellDtoReturn)));
    }

    private Mono<SeaShellDto> fetchPersonTopLowerPublisher(SeaShellDto seaShellDtoReturn) {
        return zip(
                fetchPersonCostumeTopPublisher(seaShellDtoReturn).subscribeOn(parallel()),
                fetchPersonCostumeLowerPublisher(seaShellDtoReturn).subscribeOn(parallel()),
                (persons, costumes) -> seaShellDtoReturn);
    }

    private Mono<?> fetchCostumesPublisher(Shell seaShell, SeaShellDto seaShellDtoReturn) {
        return from(just(seaShell.getCostumes()).subscribeOn(parallel()).map(shellCostumeRepository::findCostumesBlock).subscribeOn(parallel())
                .map(costumes -> {
                    costumes.forEach(costume -> seaShellDtoReturn.getCostumes().add(SeaShellConverter.toShellCostumeDto(costume)));
                    return costumes;
                })
                .thenMany(zip(
                        fetchCostumeTopPublisher(seaShellDtoReturn).subscribeOn(parallel()),
                        fetchCostumeLowerPublisher(seaShellDtoReturn).subscribeOn(parallel()),
                        (persons, costumes) -> seaShellDtoReturn)
                ));

    }

    private Mono<SeaShellDto> fetchCostumeLowerPublisher(SeaShellDto seaShellDtoReturn) {
        return fromCallable(() ->
                seaShellDtoReturn.getCostumes().parallelStream().map(seaShellCostumeDto ->
                {
                    seaShellCostumeDto.setLowerDto(
                            SeaShellConverter.toLowerDto(
                                    shellLowerRepository.findLowerByIdBlock(seaShellCostumeDto.getLowerId())));
                    return seaShellDtoReturn;
                }).findFirst().orElse(seaShellDtoReturn));
    }

    private Mono<SeaShellDto> fetchCostumeTopPublisher(SeaShellDto seaShellDtoReturn) {
        return fromCallable(() ->
                seaShellDtoReturn.getCostumes().parallelStream().map(seaShellCostumeDto ->
                {
                    seaShellCostumeDto.setTopDto(
                            SeaShellConverter.toTopDto(
                                    shellTopRepository.findTopByIdBlock(seaShellCostumeDto.getTopId())));
                    return seaShellDtoReturn;
                }).findFirst().orElse(seaShellDtoReturn));
    }

    private Mono<SeaShellDto> fetchPersonAccountPublisher(SeaShellDto seaShellDtoReturn) {
        return fromCallable(() ->
        {
            seaShellDtoReturn.getPersons()
                    .forEach(seaShellPersonDto -> seaShellPersonDto
                            .setAccountDto(
                                    SeaShellConverter
                                            .toAccountDto(shellAccountRepository
                                                    .findAccountByIdBlock(seaShellPersonDto.getAccountId()))));
            return seaShellDtoReturn;
        });
    }

    private Mono<SeaShellDto> fetchPersonCostumePublisher(SeaShellDto seaShellDtoReturn) {
        return fromCallable(() ->
        {
            seaShellDtoReturn.getPersons()
                    .forEach(seaShellPersonDto -> seaShellPersonDto
                            .setCostumeDto(
                                    SeaShellConverter
                                            .toShellCostumeDto(shellCostumeRepository
                                                    .findCostumeByIdBlock(seaShellPersonDto.getCostumeId()))));
            return seaShellDtoReturn;
        });
    }

    private Mono<SeaShellDto> fetchPersonCostumeLowerPublisher(SeaShellDto seaShellDtoReturn) {
        return fromCallable(() ->
                seaShellDtoReturn.getPersons().parallelStream().map(seaShellPersonDto -> {
                    SeaShellCostumeDto costumeDto = seaShellPersonDto.getCostumeDto();
                    costumeDto.setLowerDto(
                            SeaShellConverter.toLowerDto(
                                    shellLowerRepository.findLowerByIdBlock(costumeDto.getTopId())));
                    return seaShellDtoReturn;
                }).findFirst().orElse(seaShellDtoReturn));
    }

    private Mono<SeaShellDto> fetchPersonCostumeTopPublisher(SeaShellDto seaShellDtoReturn) {
        return fromCallable(() ->
                seaShellDtoReturn.getPersons().parallelStream().map(seaShellPersonDto -> {
                    SeaShellCostumeDto costumeDto = seaShellPersonDto.getCostumeDto();
                    costumeDto.setTopDto(
                            SeaShellConverter.toTopDto(
                                    shellTopRepository.findTopByIdBlock(costumeDto.getTopId())));
                    return seaShellDtoReturn;
                }).findFirst().orElse(seaShellDtoReturn));
    }
}
