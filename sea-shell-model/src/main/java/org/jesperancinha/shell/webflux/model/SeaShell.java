package org.jesperancinha.shell.webflux.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeaShell {
    private String name;
    private String scientificName;
    private String slogan;
    private List<SeaShellPerson> persons;
    private List<SeaShellCostume> costumes;
}

