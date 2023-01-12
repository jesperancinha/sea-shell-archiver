package org.jesperancinha.shell.webflux.immutable.repository

import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.accounts.AccountsClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class ShellAccountImmutableRepository(private val accountsClient: AccountsClient) {
    fun findAccountById(id: String?): Mono<Account?> {
        return Mono.fromCallable { accountsClient.getAccount(id) }
            .subscribeOn(Schedulers.single())
    }
}