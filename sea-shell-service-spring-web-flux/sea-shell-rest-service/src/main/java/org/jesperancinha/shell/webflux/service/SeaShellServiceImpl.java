package org.jesperancinha.shell.webflux.service;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellPersonRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.Duration.ofMillis;
import static reactor.core.scheduler.Schedulers.elastic;


@Slf4j
@Service
public class SeaShellServiceImpl extends SeaShellConsumerAdapter implements SeaShellService {

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
}
