package org.jesperancinha.shell.webflux.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String slogan;
    private List<SeaShellPersonDto> persons;
    private List<SeaShellCostumeDto> costumes;
    private List<Long> personIds;
    private List<Long> costumeIds;
}

