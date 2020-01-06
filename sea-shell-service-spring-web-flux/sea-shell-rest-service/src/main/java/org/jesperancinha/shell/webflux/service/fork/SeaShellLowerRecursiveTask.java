package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepository;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.concurrent.RecursiveTask;

@Builder
@AllArgsConstructor
public class SeaShellLowerRecursiveTask extends RecursiveTask<SeaShellCostumeDto> {

    private final ShellLowerRepository lowerRepository;
    private final SeaShellCostumeDto costumeDto;

    @Override
    protected SeaShellCostumeDto compute() {
        costumeDto.setLowerDto(SeaShellConverter.toLowerDto(lowerRepository.findLowerByIdBlock(costumeDto.getLowerId())));
        return costumeDto;
    }
}
