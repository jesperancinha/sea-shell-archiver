package org.jesperancinha.ssa.webflux;


import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Seashell
{
    private String commonName;

    private String scientificName;

    private String currency;

    private BigDecimal value;

    private ShellState shellState;

    private ShellType shellType;
}
