package org.jesperancinha.shell.webflux.immutable.data

import lombok.Builder
import org.jesperancinha.shell.client.accounts.Account
import java.math.BigDecimal

@JvmRecord
data class SeaShellAccountDto @Builder constructor(val value: BigDecimal, val currency: String) {
    companion object {
        fun create(account: Account?): SeaShellAccountDto {
            return SeaShellAccountDto.builder()
                .value(account!!.value)
                .currency(account.currency)
                .build()
        }
    }
}