package org.jesperancinha.ssa.webflux.data;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jesperancinha.ssa.webflux.model.SeaShellLocation;
import org.jesperancinha.ssa.webflux.model.ShellState;
import org.jesperancinha.ssa.webflux.model.ShellType;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SeaShellDto {

    private final Long id;

    private final String commonName;

    private final String scientificName;

    private final String currency;

    private final BigDecimal value;

    private final ShellState shellState;

    private final ShellType shellType;

    private final List<SeaShellLocation> seaShellLocations;

    @ConstructorProperties({"id", "commonName", "scientificName", "currency", "value", "shellState", "shellType", "seaShellLocations"})
    public SeaShellDto(Long id,
                       String commonName,
                       String scientificName,
                       String currency,
                       BigDecimal value,
                       ShellState shellState,
                       ShellType shellType,
                       List<SeaShellLocation> seaShellLocations) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.currency = currency;
        this.value = value;
        this.shellState = shellState;
        this.shellType = shellType;
        this.seaShellLocations = seaShellLocations;
    }
}

