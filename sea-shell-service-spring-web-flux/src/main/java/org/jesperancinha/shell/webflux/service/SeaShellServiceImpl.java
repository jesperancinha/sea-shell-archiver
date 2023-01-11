package org.jesperancinha.shell.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl;
import org.jesperancinha.shell.webflux.service.fork.SeaShellCostumesRecursiveTask;
import org.jesperancinha.shell.webflux.service.fork.SeaShellPersonsRecursiveTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

import static java.time.Duration.ofMillis;
import static java.util.stream.Collectors.toList;
import static reactor.core.scheduler.Schedulers.boundedElastic;


@Slf4j
@Service
@ConditionalOnBean(value = {
        ShellRepositoryImpl.class,
        ShellCostumeRepositoryImpl.class,
        ShellPersonRepositoryImpl.class,
        ShellAccountRepositoryImpl.class,
        ShellTopRepositoryImpl.class,
        ShellLowerRepositoryImpl.class
},
        name = {
                "shellRepositoryImpl",
                "shellCostumeRepositoryImpl",
                "shellPersonRepositoryImpl",
                "shellAccountRepositoryImpl",
                "shellTopRepositoryImpl",
                "shellLowerRepositoryImpl"
        })
public class SeaShellServiceImpl extends SeaShellConsumerAdapter {

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    @Value("${sea.shell.delay.ms:100}")
    private Integer delay;

    private final ShellRepositoryImpl shellRepository;

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
                .collect(toList());
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
                .runOn(boundedElastic());
    }

    public Flux<SeaShellDto> getAllSeaShellsReactiveWithDelay() {
        return getAllSeaShells()
                .sequential()
                .delayElements(ofMillis(delay))
                .subscribeOn(boundedElastic());
    }

    public Flux<SeaShellDto> getAllSeaShellsReactiveWithForkJoins() {
        return Flux.fromStream(shellRepository.findAllSeaShellsBlock().parallelStream()
                .map(shell -> {
                    final ForkJoinPool commonPool = new ForkJoinPool(100);
                    final SeaShellDto seaShellDto = SeaShellConverter.toShellDto(shell);
                    final Stream<ForkJoinTask<SeaShellPersonDto>> personDtoStream =
                            commonPool.invoke(getSeaShellPersonsForkJoinTask(commonPool, seaShellDto));
                    final Stream<ForkJoinTask<SeaShellCostumeDto>>
                            costumeDtoStream = commonPool.invoke(getSeaShellCostumesForkJoinTask(commonPool, seaShellDto));
                    seaShellDto.addPersons(personDtoStream.map(ForkJoinTask::join).collect(toList()));
                    seaShellDto.addCostumes(costumeDtoStream.map(ForkJoinTask::join).collect(toList()));
                    return seaShellDto;
                }));
    }

    private RecursiveTask<Stream<ForkJoinTask<SeaShellCostumeDto>>> getSeaShellCostumesForkJoinTask(
            ForkJoinPool commonPool, SeaShellDto seaShellDto) {
        return SeaShellCostumesRecursiveTask
                .builder()
                .costumeRepository(costumeRepository)
                .topRepository(topRepository)
                .lowerRepository(lowerRepository)
                .seaShellDto(seaShellDto)
                .commonPool(commonPool)
                .build();
    }

    private SeaShellPersonsRecursiveTask getSeaShellPersonsForkJoinTask(ForkJoinPool commonPool, SeaShellDto seaShellDto) {
        return SeaShellPersonsRecursiveTask.builder()
                .personRepository(personRepository)
                .accountRepository(accountRepository)
                .costumeRepository(costumeRepository)
                .topRepository(topRepository)
                .lowerRepository(lowerRepository)
                .seaShellDto(seaShellDto)
                .commonPool(commonPool).build();
    }


}
