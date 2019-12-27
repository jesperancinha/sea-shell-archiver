package org.jesperancinha.shell.webflux.data;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Setter
@EqualsAndHashCode
@ToString
public class SeaShellDto {
    @Getter
    private String name;
    @Getter
    private String scientificName;
    @Getter
    private String slogan;
    @Getter
    private List<SeaShellPersonDto> persons;
    @Getter
    private List<SeaShellCostumeDto> costumes;
    private transient List<Long> personIds;
    private transient List<Long> costumeIds;

    public List<Long> personIds(){
        return this.personIds;
    }

    public List<Long> costumeIds(){
        return this.costumeIds;
    }
}

