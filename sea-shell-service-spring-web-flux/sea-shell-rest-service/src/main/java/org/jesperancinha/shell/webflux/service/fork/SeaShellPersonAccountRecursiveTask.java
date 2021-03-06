package org.jesperancinha.shell.webflux.service.fork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.repo.ShellAccountRepository;
import org.jesperancinha.shell.webflux.service.SeaShellConverter;

import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
@Builder
public class SeaShellPersonAccountRecursiveTask extends RecursiveTask<SeaShellPersonDto> {

    private final ShellAccountRepository accountRepository;
    private final SeaShellPersonDto seaShellPersonDto;

    @Override
    protected SeaShellPersonDto compute() {
        seaShellPersonDto.setAccountDto(SeaShellConverter
                .toAccountDto(accountRepository.findAccountByIdBlock(seaShellPersonDto.getAccountId())));
        return seaShellPersonDto;
    }
}
