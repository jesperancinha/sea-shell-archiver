package org.jesperancinha.shell.webflux.immutable.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


public record SeaShellAccountDto (
    BigDecimal value,
    String currency
){
    @Builder
    public SeaShellAccountDto{
    }
}
