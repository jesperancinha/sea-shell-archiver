package org.jesperancinha.shell.webflux.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.List;


/**
 * Remember that we are testing this not as if the repository was a database but instead we are making this data
 * model as if we werer picking data from rest services.
 */
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class SeaShell {
    private final Long id;

    private final String commonName;

    private final String scientificName;

    private final String currency;

    private final BigDecimal value;

    private final ShellState shellState;

    private final ShellType shellType;

    private final List<Long> seaShellLocationListIds;

    private List<SeaShellLocation> seaShellLocations;

    @ConstructorProperties({"id", "commonName", "scientificName", "currency", "value", "shellState", "shellType", "seaShellLocationListIds", "seaShellLocations"})
    public SeaShell(Long id,
                    String commonName,
                    String scientificName,
                    String currency,
                    BigDecimal value,
                    ShellState shellState,
                    ShellType shellType,
                    List<Long> seaShellLocationListIds,
                    List<SeaShellLocation> seaShellLocations) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.currency = currency;
        this.value = value;
        this.shellState = shellState;
        this.shellType = shellType;
        this.seaShellLocationListIds = seaShellLocationListIds;
        this.seaShellLocations = seaShellLocations;
    }
}
