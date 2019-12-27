package org.jesperancinha.shell.webflux.data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SeaShellCostumeDto {
    private SeaShellTopDto topDto;
    private SeaShellLowerDto lowerDto;
    private Long topId;
    private Long lowerId;
}
