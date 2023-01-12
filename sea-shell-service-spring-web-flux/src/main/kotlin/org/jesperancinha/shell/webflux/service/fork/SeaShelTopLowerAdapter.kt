package org.jesperancinha.shell.webflux.service.fork

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask

abstract class SeaShelTopLowerAdapter<T> : RecursiveTask<T>() {
    fun getSeaShellCostumeLowerForkJoinTask(
        lowerRepository: ShellLowerRepositoryImpl, costumeDto: SeaShellCostumeDto, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> = commonPool.submit(
        SeaShellLowerRecursiveTask(
            lowerRepository = lowerRepository,
            costumeDto = costumeDto
        )
    )

    fun getSeaShellCostumeTopForkJoinTask(
        topRepository: ShellTopRepositoryImpl, costumeDto: SeaShellCostumeDto, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> = commonPool.submit(
        SeaShellTopRecursiveTask(
            topRepository = topRepository,
            costumeDto = costumeDto
        )
    )
}