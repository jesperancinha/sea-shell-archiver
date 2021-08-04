package org.jesperancinha.shell.webflux.immutable.data;

import lombok.Builder;

public record SeaShellLowerDto(
        String type,
        String color,
        String size
) {
    @Builder
    public SeaShellLowerDto {

    }
}
