package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@AllArgsConstructor
@Builder
public class SeaShellCostumeRecursiveTask extends SeaShelTopLowerAdapter<SeaShellPersonDto> {

    private final ShellCostumeRepositoryImpl costumeRepository;
    private final ShellTopRepositoryImpl topRepository;
    private final ShellLowerRepositoryImpl lowerRepository;
    private final SeaShellPersonDto seaShellPersonDto;
    private final ForkJoinPool commonPool;

    @Override
    protected SeaShellPersonDto compute() {
        seaShellPersonDto.setCostumeDto(SeaShellConverter
                .toShellCostumeDto(costumeRepository.findCostumeByIdBlock(seaShellPersonDto.getCostumeId())));
        final SeaShellCostumeDto costumeDto = seaShellPersonDto.getCostumeDto();
        final ForkJoinTask<SeaShellCostumeDto> forkTopJoinTask = getSeaShellCostumeTopForkJoinTask(topRepository, costumeDto, commonPool);
        final ForkJoinTask<SeaShellCostumeDto> forkLowerJoinTask = getSeaShellCostumeLowerForkJoinTask(lowerRepository, costumeDto, commonPool);
        forkTopJoinTask.join();
        forkLowerJoinTask.join();
        return seaShellPersonDto;
    }

}
