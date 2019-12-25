package org.jesperancinha.shell.webflux.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeaShellPersonDto {
    private String name;
    private String activity;
    private SeaShellCostumeDto costumeDto;
    private SeaShellAccountDto accountDto;
}
