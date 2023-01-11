package org.jesperancinha.shell.webflux.client.data;


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

    public void addPersons(final List<SeaShellPersonDto> persons) {
        this.persons.addAll(persons);
    }

    public void addCostumes(final List<SeaShellCostumeDto> costumes) {
        this.costumes.addAll(costumes);
    }
}
