package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.accounts.Account;
import org.jesperancinha.shell.client.accounts.AccountsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellAccountRepositoryImpl {

    private final AccountsClient accountsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellAccountRepositoryImpl(final AccountsClient accountsClient) {
        this.accountsClient = accountsClient;
    }

    public Mono<Account> findAccountById(String id) {
        return Mono.fromCallable(() -> accountsClient.getAccount(id))
                .subscribeOn(single());
    }

    public Account findAccountByIdBlock(String id) {
        return accountsClient.getAccount(id);
    }

}
