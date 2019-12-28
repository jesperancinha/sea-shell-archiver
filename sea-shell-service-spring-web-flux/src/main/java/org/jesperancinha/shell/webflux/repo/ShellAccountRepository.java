package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.accounts.Account;
import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAccountsAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Repository
public class ShellAccountRepository {

    private final SeaShellsWSDLAccountsAbstract seaShellsWSDLAccountsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellAccountRepository(SeaShellsWSDLAccountsAbstract seaShellsWSDLAccountsClient) {
        this.seaShellsWSDLAccountsClient = seaShellsWSDLAccountsClient;
    }

    public Mono<Account> findAccountById(String id) {
        return Mono.fromCallable(()-> seaShellsWSDLAccountsClient.getItem(id))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Account findAccountByIdBlock(String id) {
        return seaShellsWSDLAccountsClient.getItem(id);
    }

}
