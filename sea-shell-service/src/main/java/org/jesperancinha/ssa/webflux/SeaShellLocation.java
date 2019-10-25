package org.jesperancinha.ssa.webflux;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SeaShellLocation
{

    private final Long id;

    private final BigDecimal lan;
    private final BigDecimal lon;

    private final String designation;
}
