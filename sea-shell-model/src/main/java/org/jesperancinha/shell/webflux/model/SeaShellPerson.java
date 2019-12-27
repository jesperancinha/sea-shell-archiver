package org.jesperancinha.shell.webflux.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeaShellPerson {
    private String name;
    private String activity;
    private SeaShellCostume costumeDto;
    private SeaShellAccount accountDto;
}


