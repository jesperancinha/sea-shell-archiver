package org.jesperancinha.shell.webflux.model;


import java.util.List;

public record SeaShell(
        String name,
        String scientificName,
        String slogan,
        List<SeaShellPerson> persons,
        List<SeaShellCostume> costumes
) {

}

