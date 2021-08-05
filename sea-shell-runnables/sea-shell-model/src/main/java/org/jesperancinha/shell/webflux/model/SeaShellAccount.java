package org.jesperancinha.shell.webflux.model;

import java.math.BigDecimal;

public record SeaShellAccount(
        BigDecimal value,
        String currency
) {
}
