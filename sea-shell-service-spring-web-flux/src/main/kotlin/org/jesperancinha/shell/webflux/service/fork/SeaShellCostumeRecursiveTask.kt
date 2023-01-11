package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepositoryImpl
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.ForkJoinPool

@AllArgsConstructor
@Builder
class SeaShellCostumeRecursiveTask(
    private val costumeRepository: ShellCostumeRepositoryImpl,
    private val topRepository: ShellTopRepositoryImpl,
    private val lowerRepository: ShellLowerRepositoryImpl,
    private val seaShellPersonDto: SeaShellPersonDto,
    private val commonPool: ForkJoinPool
) : SeaShelTopLowerAdapter<SeaShellPersonDto?>() {

    override fun compute(): SeaShellPersonDto = seaShellPersonDto.copy(
        costumeDto = SeaShellConverter.toShellCostumeDto(
            costumeRepository.findCostumeByIdBlock(
                seaShellPersonDto.costumeId
            )
        )
    ).also {
        val costumeDto = it.costumeDto
        val forkTopJoinTask = getSeaShellCostumeTopForkJoinTask(topRepository, costumeDto, commonPool)
        val forkLowerJoinTask = getSeaShellCostumeLowerForkJoinTask(lowerRepository, costumeDto, commonPool)
        forkTopJoinTask.join()
        forkLowerJoinTask.join()
    }
}