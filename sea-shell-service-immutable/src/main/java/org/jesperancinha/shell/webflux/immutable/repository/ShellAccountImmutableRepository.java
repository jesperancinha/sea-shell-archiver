package org.jesperancinha.shell.webflux.immutable.repository;

import org.jesperancinha.shell.client.accounts.Account;
import org.jesperancinha.shell.client.accounts.AccountsClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellAccountImmutableRepository {

    private final AccountsClient accountsClient;

    public ShellAccountImmutableRepository(final AccountsClient accountsClient) {
        this.accountsClient = accountsClient;
    }

    public Mono<Account> findAccountById(String id) {
        return Mono.fromCallable(() -> accountsClient.getAccount(id))
                .subscribeOn(single());
    }

}
