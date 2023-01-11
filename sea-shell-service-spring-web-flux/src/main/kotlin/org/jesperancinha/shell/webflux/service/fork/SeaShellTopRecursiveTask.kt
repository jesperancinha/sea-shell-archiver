package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.repo.ShellTopRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.RecursiveTask

@Builder
@AllArgsConstructor
class SeaShellTopRecursiveTask : RecursiveTask<SeaShellCostumeDto?>() {
    private val topRepository: ShellTopRepositoryImpl? = null
    private val costumeDto: SeaShellCostumeDto? = null
    override fun compute(): SeaShellCostumeDto? {
        costumeDto.setTopDto(SeaShellConverter.toTopDto(topRepository!!.findTopByIdBlock(costumeDto.getTopId())))
        return costumeDto
    }
}