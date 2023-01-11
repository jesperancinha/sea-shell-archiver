package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.data.SeaShellDto
import org.jesperancinha.shell.webflux.repo.ShellCostumeRepositoryImpl
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.stream.Stream

@Builder
@AllArgsConstructor
class SeaShellCostumesRecursiveTask : SeaShelTopLowerAdapter<Stream<ForkJoinTask<SeaShellCostumeDto?>?>?>() {
    private val costumeRepository: ShellCostumeRepositoryImpl? = null
    private val topRepository: ShellTopRepositoryImpl? = null
    private val lowerRepository: ShellLowerRepositoryImpl? = null
    private val seaShellDto: SeaShellDto? = null
    private val commonPool: ForkJoinPool? = null
    override fun compute(): Stream<ForkJoinTask<SeaShellCostumeDto?>?> {
        val costumesBlock = costumeRepository!!.findCostumesBlock(seaShellDto!!.costumeIds)
        return costumesBlock!!.parallelStream().map { obj: Costume? -> SeaShellConverter.toShellCostumeDto() }
            .flatMap { seaShellCostumeDto: SeaShellCostumeDto? ->
                Stream.of(
                    getSeaShellCostumeTopForkJoinTask(
                        topRepository, seaShellCostumeDto, commonPool!!
                    ),
                    getSeaShellCostumeLowerForkJoinTask(
                        lowerRepository, seaShellCostumeDto, commonPool
                    )
                )
            }
    }
}