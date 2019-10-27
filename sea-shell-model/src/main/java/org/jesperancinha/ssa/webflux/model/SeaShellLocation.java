package org.jesperancinha.ssa.webflux.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class SeaShellLocation {

    private final Long id;

    private final BigDecimal lan;
    private final BigDecimal lon;

    private final String designation;
}
