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
class SeaShellCostumesRecursiveTask(
    private val costumeRepository: ShellCostumeRepositoryImpl,
    private val topRepository: ShellTopRepositoryImpl,
    private val lowerRepository: ShellLowerRepositoryImpl,
    private val seaShellDto: SeaShellDto,
    private val commonPool: ForkJoinPool
) : SeaShelTopLowerAdapter<Stream<ForkJoinTask<SeaShellCostumeDto>>>() {

    override fun compute(): Stream<ForkJoinTask<SeaShellCostumeDto>> {
        val costumesBlock = costumeRepository.findCostumesBlock(seaShellDto.costumeIds)
        return costumesBlock.map { costume -> SeaShellConverter.toShellCostumeDto(costume) }
            .stream().flatMap { seaShellCostumeDto ->
                Stream.of(
                    getSeaShellCostumeTopForkJoinTask(
                        topRepository = topRepository, costumeDto = seaShellCostumeDto, commonPool = commonPool
                    ),
                    getSeaShellCostumeLowerForkJoinTask(
                        lowerRepository = lowerRepository, costumeDto = seaShellCostumeDto, commonPool = commonPool
                    )
                )
            }
    }
}