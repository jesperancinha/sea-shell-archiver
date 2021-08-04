package org.jesperancinha.shell.webflux.immutable.data;

import lombok.Builder;
import org.jesperancinha.shell.client.accounts.Account;

import java.math.BigDecimal;


public record SeaShellAccountDto(
        BigDecimal value,
        String currency
) {
    @Builder
    public SeaShellAccountDto {
    }

    public static SeaShellAccountDto create(Account account) {
        return SeaShellAccountDto.builder()
                .value(account.getValue())
                .currency(account.getCurrency())
                .build();
    }
}
