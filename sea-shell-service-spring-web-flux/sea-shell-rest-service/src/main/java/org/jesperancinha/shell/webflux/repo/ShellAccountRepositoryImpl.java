package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.accounts.Account;
import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAccountsAbstract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;


@Repository
public class ShellAccountRepositoryImpl implements ShellAccountRepository {

    private final SeaShellsWSDLAccountsAbstract seaShellsWSDLAccountsClient;

    @Value("${sea.shell.parallelism:20}")
    private Integer parallelism;

    public ShellAccountRepositoryImpl(SeaShellsWSDLAccountsAbstract seaShellsWSDLAccountsClient) {
        this.seaShellsWSDLAccountsClient = seaShellsWSDLAccountsClient;
    }

    public Mono<Account> findAccountById(String id) {
        return Mono.fromCallable(() -> seaShellsWSDLAccountsClient.getItem(id))
                .subscribeOn(single());
    }

    public Account findAccountByIdBlock(String id) {
        return seaShellsWSDLAccountsClient.getItem(id);
    }

}
