package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.repo.*;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

@AllArgsConstructor
@Builder
public class SeaShellPersonsRecursiveTask extends RecursiveTask<Stream<ForkJoinTask<SeaShellPersonDto>>> {

    private final ShellPersonRepositoryImpl personRepository;
    private final ShellAccountRepositoryImpl accountRepository;
    private final ShellCostumeRepositoryImpl costumeRepository;
    private final ShellTopRepositoryImpl topRepository;
    private final ShellLowerRepositoryImpl lowerRepository;
    private final SeaShellDto seaShellDto;
    private final ForkJoinPool commonPool;


    @Override
    protected Stream<ForkJoinTask<SeaShellPersonDto>> compute() {
        List<Person> personsBlock = personRepository.findPersonsBlock(seaShellDto.personIds());
        return personsBlock.parallelStream().map(SeaShellConverter::toShellPersonDto)
                .flatMap(seaShellPersonDto -> getSeaShellPersonForkJoinTaskStream(seaShellPersonDto, commonPool));
    }

    private Stream<ForkJoinTask<SeaShellPersonDto>> getSeaShellPersonForkJoinTaskStream(
            SeaShellPersonDto seaShellPersonDto, ForkJoinPool commonPool) {
        return Stream.of(
                commonPool.submit(SeaShellPersonAccountRecursiveTask
                        .builder().accountRepository(accountRepository)
                        .seaShellPersonDto(seaShellPersonDto).build()),
                commonPool.submit(SeaShellCostumeRecursiveTask
                        .builder().costumeRepository(costumeRepository)
                        .topRepository(topRepository)
                        .lowerRepository(lowerRepository)
                        .seaShellPersonDto(seaShellPersonDto)
                        .commonPool(commonPool)
                        .build())
        );
    }

}
