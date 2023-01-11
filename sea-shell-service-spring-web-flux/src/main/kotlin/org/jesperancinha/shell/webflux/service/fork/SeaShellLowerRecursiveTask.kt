package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.repo.ShellLowerRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.RecursiveTask

@Builder
@AllArgsConstructor
class SeaShellLowerRecursiveTask : RecursiveTask<SeaShellCostumeDto?>() {
    private val lowerRepository: ShellLowerRepositoryImpl? = null
    private val costumeDto: SeaShellCostumeDto? = null
    override fun compute(): SeaShellCostumeDto? {
        costumeDto.setLowerDto(SeaShellConverter.toLowerDto(lowerRepository!!.findLowerByIdBlock(costumeDto.getLowerId())))
        return costumeDto
    }
}