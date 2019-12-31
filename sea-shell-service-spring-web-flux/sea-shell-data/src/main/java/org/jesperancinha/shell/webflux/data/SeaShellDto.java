package org.jesperancinha.shell.webflux.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeaShellDto {
    @Default
    private String name = null;
    @Default
    private String scientificName = null;
    @Default
    private String slogan = null;
    @Default
    private List<SeaShellPersonDto> persons = new ArrayList<>();
    @Default
    private List<SeaShellCostumeDto> costumes = new ArrayList<>();
    @Default
    private List<Long> personIds = new ArrayList<>();
    @Default
    private List<Long> costumeIds = new ArrayList<>();
}

