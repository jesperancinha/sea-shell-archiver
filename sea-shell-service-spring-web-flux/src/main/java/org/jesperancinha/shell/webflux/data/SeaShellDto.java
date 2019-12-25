package org.jesperancinha.shell.webflux.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jesperancinha.shell.webflux.model.SeaShellLocation;
import org.jesperancinha.shell.webflux.model.ShellState;
import org.jesperancinha.shell.webflux.model.ShellType;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeaShellDto {
    private String name;
    private String scientificName;
    private List<SeaShellPersonDto> persons;
    private List<SeaShellCostumeDto> costumes;
}

