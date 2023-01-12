package org.jesperancinha.shell.webflux.immutable.data

import org.jesperancinha.shell.client.accounts.Account
import java.math.BigDecimal

data class SeaShellAccountDto(val value: BigDecimal, val currency: String) {
    companion object {
        fun create(account: Account) = SeaShellAccountDto(
            value = account.value,
            currency = account.currency
        )
    }
}