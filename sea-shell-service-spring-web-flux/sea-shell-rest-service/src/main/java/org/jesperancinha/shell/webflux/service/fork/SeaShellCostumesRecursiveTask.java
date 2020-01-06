package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;

@Builder
@AllArgsConstructor
public class SeaShellCostumesRecursiveTask extends SeaShelTopLowerAdapter<Stream<ForkJoinTask<SeaShellCostumeDto>>> {

    private final ShellCostumeRepository costumeRepository;
    private final ShellTopRepository topRepository;
    private final ShellLowerRepository lowerRepository;
    private final SeaShellDto seaShellDto;
    private final ForkJoinPool commonPool;

    @Override
    protected Stream<ForkJoinTask<SeaShellCostumeDto>> compute() {
        List<Costume> costumesBlock = costumeRepository.findCostumesBlock(seaShellDto.getCostumeIds());

        return costumesBlock.parallelStream().map(SeaShellConverter::toShellCostumeDto)
                .flatMap(seaShellCostumeDto -> of(
                        getSeaShellCostumeTopForkJoinTask(
                                topRepository, seaShellCostumeDto, commonPool),
                        getSeaShellCostumeLowerForkJoinTask(
                                lowerRepository, seaShellCostumeDto, commonPool)
                ));
    }
}
