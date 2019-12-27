package org.jesperancinha.shell.webflux.data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@EqualsAndHashCode
@ToString
public class SeaShellCostumeDto {
    @Getter
    private SeaShellTopDto topDto;
    @Getter
    private SeaShellLowerDto lowerDto;
    private transient Long topId;
    private transient Long lowerId;

    public Long topId() {
        return this.topId;
    }

    public Long lowerId() {
        return this.lowerId;
    }
}
