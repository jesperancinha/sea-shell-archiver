package org.jesperancinha.ssa.webflux;


import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;


/**
 * Remember that we are testing this not as if the repository was a database but instead we are making this data
 * model as if we werer picking data from rest services.
 */
@Builder
@Getter
public class SeaShell
{
    private final Long id;

    private final String commonName;

    private final String scientificName;

    private final String currency;

    private final BigDecimal value;

    private final ShellState shellState;

    private final ShellType shellType;

    private final List<Long> seaShellLocationListIds;
}
