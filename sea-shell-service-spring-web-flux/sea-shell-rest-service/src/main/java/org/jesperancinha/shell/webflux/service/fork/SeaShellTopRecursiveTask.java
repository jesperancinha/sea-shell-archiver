package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.repo.ShellTopRepository;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.concurrent.RecursiveTask;

@Builder
@AllArgsConstructor
public class SeaShellTopRecursiveTask extends RecursiveTask<SeaShellCostumeDto> {

    private final ShellTopRepository topRepository;
    private final SeaShellCostumeDto costumeDto;

    @Override
    protected SeaShellCostumeDto compute() {
        costumeDto.setTopDto(SeaShellConverter.toTopDto(topRepository.findTopByIdBlock(costumeDto.getTopId())));
        return costumeDto;
    }
}
