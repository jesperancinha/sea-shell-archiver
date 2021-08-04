package org.jesperancinha.shell.webflux.immutable.data;

import lombok.Builder;

public record SeaShellPersonDto(
        String name,
        String activity,
        SeaShellCostumeDto costumeDto,
        SeaShellAccountDto accountDto,
        Long costumeId,
        String accountId
) {
    @Builder
    public SeaShellPersonDto {
    }
}
