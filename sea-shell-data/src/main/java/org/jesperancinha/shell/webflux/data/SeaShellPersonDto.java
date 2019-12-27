package org.jesperancinha.shell.webflux.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeaShellPersonDto {
    @Getter
    private String name;
    @Getter
    private String activity;
    @Getter
    private SeaShellCostumeDto costumeDto;
    @Getter
    private SeaShellAccountDto accountDto;
    private transient Long costumeId;
    private transient String accountId;

    public Long costumeId() {
        return this.costumeId;
    }

    public String accountId() {
        return this.accountId;
    }
}
