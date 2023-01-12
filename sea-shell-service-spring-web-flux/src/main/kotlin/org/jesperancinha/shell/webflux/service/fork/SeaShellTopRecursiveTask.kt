package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.RecursiveTask

@Builder
@AllArgsConstructor
class SeaShellTopRecursiveTask(
    val topRepository: ShellTopRepositoryImpl,
    val costumeDto: SeaShellCostumeDto
) : RecursiveTask<SeaShellCostumeDto>() {

    override fun compute(): SeaShellCostumeDto =
        costumeDto.copy(topDto = costumeDto.topId?.let {
            topRepository.findTopByIdBlock(it)
        }?.let { SeaShellConverter.toTopDto(it) })
}