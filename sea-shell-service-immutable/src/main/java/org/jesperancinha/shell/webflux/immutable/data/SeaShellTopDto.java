package org.jesperancinha.shell.webflux.immutable.data;

import lombok.Builder;

public record SeaShellTopDto(
        String type,
        String color,
        String size
) {
    @Builder
    public SeaShellTopDto {

    }
}
