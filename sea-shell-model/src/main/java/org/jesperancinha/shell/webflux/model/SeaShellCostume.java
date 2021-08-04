package org.jesperancinha.shell.webflux.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SeaShellCostume {
    private SeaShellTop topDto;
    private SeaShellLower lowerDto;
}
