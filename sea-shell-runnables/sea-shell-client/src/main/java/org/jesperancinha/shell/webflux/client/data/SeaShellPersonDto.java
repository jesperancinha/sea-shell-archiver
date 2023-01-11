package org.jesperancinha.shell.webflux.client.data;

import lombok.*;

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
    private transient Long costumeId;
    private transient String accountId;
}
