package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.concurrent.RecursiveTask;

@Builder
@AllArgsConstructor
public class SeaShellTopRecursiveTask extends RecursiveTask<SeaShellCostumeDto> {

    private final ShellTopRepositoryImpl topRepository;
    private final SeaShellCostumeDto costumeDto;

    @Override
    protected SeaShellCostumeDto compute() {
        costumeDto.setTopDto(SeaShellConverter.toTopDto(topRepository.findTopByIdBlock(costumeDto.getTopId())));
        return costumeDto;
    }
}
