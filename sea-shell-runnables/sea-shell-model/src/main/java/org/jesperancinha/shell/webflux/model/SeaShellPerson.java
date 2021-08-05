package org.jesperancinha.shell.webflux.model;

public record SeaShellPerson(
        String name,
        String activity,
        SeaShellCostume costumeDto,
        SeaShellAccount accountDto
) {
}


