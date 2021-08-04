package org.jesperancinha.shell.webflux.immutable.data;


import lombok.Builder;

import java.util.List;

public record SeaShellDto(
        String name,
        String scientificName,
        String slogan,
        List<SeaShellPersonDto> persons,
        List<SeaShellCostumeDto> costumes,
        List<Long> personIds,
        List<Long> costumeIds) {

    @Builder
    public SeaShellDto {
    }
}
