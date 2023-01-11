package org.jesperancinha.shell.webflux.service.fork

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask

abstract class SeaShelTopLowerAdapter<T> : RecursiveTask<T>() {
    fun getSeaShellCostumeLowerForkJoinTask(
        lowerRepository: ShellLowerRepositoryImpl?, costumeDto: SeaShellCostumeDto?, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> {
        return commonPool.submit(
            SeaShellLowerRecursiveTask.builder()
                .lowerRepository(lowerRepository)
                .costumeDto(costumeDto).build()
        )
    }

    fun getSeaShellCostumeTopForkJoinTask(
        topRepository: ShellTopRepositoryImpl?, costumeDto: SeaShellCostumeDto?, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> {
        return commonPool.submit(
            SeaShellTopRecursiveTask.builder()
                .topRepository(topRepository)
                .costumeDto(costumeDto).build()
        )
    }
}