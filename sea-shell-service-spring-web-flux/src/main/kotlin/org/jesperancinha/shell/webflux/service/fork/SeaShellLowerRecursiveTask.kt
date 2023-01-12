package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.RecursiveTask

@Builder
@AllArgsConstructor
class SeaShellLowerRecursiveTask(
    private val lowerRepository: ShellLowerRepositoryImpl,
    private val costumeDto: SeaShellCostumeDto
) : RecursiveTask<SeaShellCostumeDto>() {

    override fun compute(): SeaShellCostumeDto =
        costumeDto.copy(lowerDto = costumeDto.lowerId
            ?.let { lowerRepository.findLowerByIdBlock(it) }
            ?.let { SeaShellConverter.toLowerDto(it) })
}