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
class SeaShellCostumeRecursiveTask : SeaShelTopLowerAdapter<SeaShellPersonDto?>() {
    private val costumeRepository: ShellCostumeRepositoryImpl? = null
    private val topRepository: ShellTopRepositoryImpl? = null
    private val lowerRepository: ShellLowerRepositoryImpl? = null
    private val seaShellPersonDto: SeaShellPersonDto? = null
    private val commonPool: ForkJoinPool? = null
    override fun compute(): SeaShellPersonDto {
        seaShellPersonDto.setCostumeDto(
            SeaShellConverter.toShellCostumeDto(
                costumeRepository!!.findCostumeByIdBlock(
                    seaShellPersonDto.getCostumeId()
                )
            )
        )
        val costumeDto = seaShellPersonDto.getCostumeDto()
        val forkTopJoinTask = getSeaShellCostumeTopForkJoinTask(topRepository, costumeDto, commonPool!!)
        val forkLowerJoinTask = getSeaShellCostumeLowerForkJoinTask(lowerRepository, costumeDto, commonPool)
        forkTopJoinTask!!.join()
        forkLowerJoinTask!!.join()
        return seaShellPersonDto!!
    }
}