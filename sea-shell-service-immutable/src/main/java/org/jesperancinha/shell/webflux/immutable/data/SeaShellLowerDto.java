package org.jesperancinha.shell.webflux.immutable.data;

import lombok.Builder;
import org.jesperancinha.shell.client.lowers.Lower;

public record SeaShellLowerDto(
        String type,
        String color,
        String size
) {
    @Builder
    public SeaShellLowerDto {

    }

    public static SeaShellLowerDto create(Lower lower) {
        return SeaShellLowerDto.builder()
                .type(lower.getType())
                .color(lower.getColor())
                .size(lower.getSize())
                .build();
    }
}
