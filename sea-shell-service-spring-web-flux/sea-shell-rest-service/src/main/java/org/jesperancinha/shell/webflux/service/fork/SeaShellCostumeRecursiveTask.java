package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepository;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@AllArgsConstructor
@Builder
public class SeaShellCostumeRecursiveTask extends SeaShelTopLowerAdapter<SeaShellPersonDto> {

    private final ShellCostumeRepository costumeRepository;
    private final ShellTopRepository topRepository;
    private final ShellLowerRepository lowerRepository;
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
