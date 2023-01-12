package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.ShellAccountRepositoryImpl
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.RecursiveTask

@AllArgsConstructor
@Builder
class SeaShellPersonAccountRecursiveTask(
    private val accountRepository: ShellAccountRepositoryImpl,
    private val seaShellPersonDto: SeaShellPersonDto
) : RecursiveTask<SeaShellPersonDto>() {

    override fun compute(): SeaShellPersonDto = seaShellPersonDto.copy(
        accountDto = seaShellPersonDto.accountId
            ?.let { accountRepository.findAccountByIdBlock(it) }
            ?.let {
                SeaShellConverter.toAccountDto(it)
            }
    )
}
