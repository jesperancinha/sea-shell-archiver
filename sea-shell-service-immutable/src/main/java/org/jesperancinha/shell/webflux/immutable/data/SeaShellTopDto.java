package org.jesperancinha.shell.webflux.immutable.data;

import lombok.Builder;
import org.jesperancinha.shell.client.tops.Top;

public record SeaShellTopDto(
        String type,
        String color,
        String size
) {
    @Builder
    public SeaShellTopDto {

    }

    public static SeaShellTopDto create(Top top) {
        return SeaShellTopDto.builder()
                .type(top.getType())
                .color(top.getColor())
                .size(top.getSize())
                .build();
    }
}
