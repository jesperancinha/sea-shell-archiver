package org.jesperancinha.shell.webflux.client.data;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeaShellTopDto {
    private String type;
    private String color;
    private String size;
}
