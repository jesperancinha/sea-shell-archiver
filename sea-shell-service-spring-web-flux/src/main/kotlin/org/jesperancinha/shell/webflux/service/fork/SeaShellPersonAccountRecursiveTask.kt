package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.ShellAccountRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.RecursiveTask

@AllArgsConstructor
@Builder
class SeaShellPersonAccountRecursiveTask : RecursiveTask<SeaShellPersonDto?>() {
    private val accountRepository: ShellAccountRepositoryImpl? = null
    private val seaShellPersonDto: SeaShellPersonDto? = null
    override fun compute(): SeaShellPersonDto? {
        seaShellPersonDto.setAccountDto(
            SeaShellConverter.toAccountDto(
                accountRepository!!.findAccountByIdBlock(
                    seaShellPersonDto.getAccountId()
                )
            )
        )
        return seaShellPersonDto
    }
}