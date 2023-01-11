package org.jesperancinha.shell.webflux.service.fork;

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl;
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public abstract class SeaShelTopLowerAdapter<T> extends RecursiveTask<T> {

    public ForkJoinTask<SeaShellCostumeDto> getSeaShellCostumeLowerForkJoinTask(
            ShellLowerRepositoryImpl lowerRepository, SeaShellCostumeDto costumeDto, ForkJoinPool commonPool) {
        return commonPool.submit(
                SeaShellLowerRecursiveTask.builder()
                        .lowerRepository(lowerRepository)
                        .costumeDto(costumeDto).build());
    }

    public ForkJoinTask<SeaShellCostumeDto> getSeaShellCostumeTopForkJoinTask(
            ShellTopRepositoryImpl topRepository, SeaShellCostumeDto costumeDto, ForkJoinPool commonPool) {
        return commonPool.submit(
                SeaShellTopRecursiveTask.builder()
                        .topRepository(topRepository)
                        .costumeDto(costumeDto).build());
    }
}
