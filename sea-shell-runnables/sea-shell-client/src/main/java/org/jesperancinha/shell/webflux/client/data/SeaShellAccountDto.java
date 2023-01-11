package org.jesperancinha.shell.webflux.client.data;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeaShellAccountDto {
    private BigDecimal value;
    private String currency;
}
