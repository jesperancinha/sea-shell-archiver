package org.jesperancinha.shell.webflux.data

import lombok.*

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
class SeaShellLowerDto {
    private val type: String? = null
    private val color: String? = null
    private val size: String? = null
}