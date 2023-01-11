package org.jesperancinha.shell.webflux.repo

import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.accounts.AccountsClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Repository
class ShellAccountRepositoryImpl(private val accountsClient: AccountsClient) {
    @Value("\${sea.shell.parallelism:20}")
    private val parallelism: Int? = null
    fun findAccountById(id: String?): Mono<Account?> {
        return Mono.fromCallable { accountsClient.getAccount(id) }
            .subscribeOn(Schedulers.single())
    }

    fun findAccountByIdBlock(id: String?): Account {
        return accountsClient.getAccount(id)
    }
}