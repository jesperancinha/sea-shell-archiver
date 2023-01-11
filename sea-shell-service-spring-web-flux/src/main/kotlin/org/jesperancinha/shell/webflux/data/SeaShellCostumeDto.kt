package org.jesperancinha.shell.webflux.data

import lombok.*

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
class SeaShellCostumeDto {
    private val topDto: SeaShellTopDto? = null
    private val lowerDto: SeaShellLowerDto? = null
    private val topId: Long? = null
    private val lowerId: Long? = null
}