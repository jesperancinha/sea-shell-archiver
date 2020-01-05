package org.jesperancinha.shell.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.jesperancinha.shell.webflux.repo.ShellRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.Duration.ofMillis;
import static java.util.stream.Stream.of;
import static reactor.core.scheduler.Schedulers.elastic;


@Slf4j
@Service
public class SeaShellServiceImpl extends SeaShellConsumerAdapter implements SeaShellService {

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    @Value("${sea.shell.delay.ms:100}")
    private Integer delay;

    private final ShellRepository shellRepository;

    public SeaShellServiceImpl(ShellRepositoryImpl shellRepository,
                               ShellCostumeRepositoryImpl costumeRepository,
                               ShellPersonRepositoryImpl personRepository,
                               ShellAccountRepositoryImpl accountRepository,
                               ShellTopRepositoryImpl topRepository,
                               ShellLowerRepositoryImpl lowerRepository) {
        super(costumeRepository,
                personRepository,
                accountRepository,
                topRepository,
                lowerRepository);
        this.shellRepository = shellRepository;
    }

    public Mono<SeaShellDto> getSeaShellById(Long id) {
        return shellRepository.findSeaShellById(id)
                .map(SeaShellConverter::toShellDto)
                .doOnNext(consumerPersons())
                .doOnNext(consumerCostumes());
    }

    public ParallelFlux<SeaShellDto> getAllSeaShells() {
        return shellRepository
                .findAllSeaShells()
                .map(SeaShellConverter::toShellDto)
                .doOnNext(consumerPersons())
                .doOnNext(consumerCostumes());
    }

    public List<SeaShellDto> getAllSeaShellsNaifBlock() {
        return shellRepository.findAllSeaShellsBlock()
                .parallelStream()
                .map(SeaShellConverter::toShellDto)
                .peek(this::setMainRootElements)
                .collect(Collectors.toList());
    }

    public SeaShellDto getSeaShellNaifBlock(Long id) {
        final SeaShellDto seaShellDto = SeaShellConverter
                .toShellDto(shellRepository
                        .findSeaShellBlockById(id));
        setMainRootElements(seaShellDto);
        return seaShellDto;
    }

    public ParallelFlux<SeaShellDto> getAllSeaShellsReactiveBlock() {
        return Mono.fromCallable(this::getAllSeaShellsNaifBlock)
                .flux().flatMap(Flux::fromIterable)
                .parallel(parallelism)
                .runOn(elastic());
    }

    public Flux<SeaShellDto> getAllSeaShellsReactiveWithDelay() {
        return getAllSeaShells()
                .sequential()
                .delayElements(ofMillis(delay))
                .subscribeOn(elastic());
    }

    public Flux<SeaShellDto> getAllSeaShellsReactiveWithForkJoins() {
        ForkJoinPool commonPool = new ForkJoinPool(100);
        List<Shell> allSeaShellsBlock = shellRepository.findAllSeaShellsBlock();
        return Flux.fromIterable(allSeaShellsBlock.parallelStream()
                .map(shell -> {
                    SeaShellDto seaShellDto = SeaShellConverter.toShellDto(shell);

                    Stream<ForkJoinTask<SeaShellPersonDto>> personDtos = commonPool.invoke(new RecursiveTask<Stream<ForkJoinTask<SeaShellPersonDto>>>() {
                        @Override
                        protected Stream<ForkJoinTask<SeaShellPersonDto>> compute() {
                            List<Person> personsBlock = personRepository.findPersonsBlock(seaShellDto.getPersonIds());

                            return personsBlock.parallelStream().map(SeaShellConverter::toShellPersonDto)
                                    .flatMap(seaShellPersonDto -> of(commonPool.submit(new RecursiveTask<>() {
                                                @Override
                                                protected SeaShellPersonDto compute() {
                                                    seaShellPersonDto.setAccountDto(SeaShellConverter
                                                            .toAccountDto(accountRepository.findAccountByIdBlock(seaShellPersonDto.getAccountId())));
                                                    return seaShellPersonDto;
                                                }
                                            }),
                                            commonPool.submit(new RecursiveTask<>() {
                                                @Override
                                                protected SeaShellPersonDto compute() {
                                                    seaShellPersonDto.setCostumeDto(SeaShellConverter
                                                            .toShellCostumeDto(costumeRepository.findCostumeByIdBlock(seaShellPersonDto.getCostumeId())));
                                                    SeaShellCostumeDto costumeDto = seaShellPersonDto.getCostumeDto();
                                                    ForkJoinTask<SeaShellCostumeDto> forkTopJoinTask = getSeaShellCostumeTopForkJoinTask(costumeDto, commonPool);
                                                    ForkJoinTask<SeaShellCostumeDto> forkLowerJoinTask = getSeaShellCostumeLowerForkJoinTask(costumeDto, commonPool);
                                                    forkTopJoinTask.join();
                                                    forkLowerJoinTask.join();
                                                    return seaShellPersonDto;
                                                }
                                            })));
                        }
                    });
                    Stream<ForkJoinTask<SeaShellCostumeDto>> costumeDtos = commonPool.invoke(new RecursiveTask<>() {
                        @Override
                        protected Stream<ForkJoinTask<SeaShellCostumeDto>> compute() {
                            List<Costume> costumesBlock = costumeRepository.findCostumesBlock(seaShellDto.getCostumeIds());

                            return costumesBlock.parallelStream().map(SeaShellConverter::toShellCostumeDto)
                                    .flatMap(seaShellCostumeDto -> of(
                                            getSeaShellCostumeTopForkJoinTask(seaShellCostumeDto, commonPool),
                                            getSeaShellCostumeLowerForkJoinTask(seaShellCostumeDto, commonPool)
                                    ));
                        }
                    });
                    seaShellDto.setPersons(personDtos.map(ForkJoinTask::join).collect(Collectors.toList()));
                    seaShellDto.setCostumes(costumeDtos.map(ForkJoinTask::join).collect(Collectors.toList()));
                    return seaShellDto;
                }).collect(Collectors.toList()));


    }

    private ForkJoinTask<SeaShellCostumeDto> getSeaShellCostumeLowerForkJoinTask(SeaShellCostumeDto costumeDto, ForkJoinPool commonPool) {
        return commonPool.submit(new RecursiveTask<>() {
            @Override
            protected SeaShellCostumeDto compute() {
                costumeDto.setLowerDto(SeaShellConverter.toLowerDto(lowerRepository.findLowerByIdBlock(costumeDto.getLowerId())));
                return costumeDto;
            }
        });
    }

    private ForkJoinTask<SeaShellCostumeDto> getSeaShellCostumeTopForkJoinTask(SeaShellCostumeDto costumeDto, ForkJoinPool commonPool) {
        return commonPool.submit(new RecursiveTask<>() {
            @Override
            protected SeaShellCostumeDto compute() {
                costumeDto.setTopDto(SeaShellConverter.toTopDto(topRepository.findTopByIdBlock(costumeDto.getTopId())));
                return costumeDto;
            }
        });
    }

}
