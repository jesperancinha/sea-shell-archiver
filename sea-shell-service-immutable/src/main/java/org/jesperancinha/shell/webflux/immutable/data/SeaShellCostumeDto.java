package org.jesperancinha.shell.webflux.immutable.data;

import lombok.Builder;

public record SeaShellCostumeDto(
        SeaShellTopDto topDto,
        SeaShellLowerDto lowerDto,
        Long topId,
        Long lowerId
) {
    @Builder
    public SeaShellCostumeDto {
    }
}
