package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.accounts.Account;
import reactor.core.publisher.Mono;

public interface ShellAccountRepository {
    Mono<Account> findAccountById(String id);

    Account findAccountByIdBlock(String id);
}
