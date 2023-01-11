package org.jesperancinha.shell.webflux.client.data;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeaShellCostumeDto {
    private SeaShellTopDto topDto;
    private SeaShellLowerDto lowerDto;
    private Long topId;
    private Long lowerId;
}
