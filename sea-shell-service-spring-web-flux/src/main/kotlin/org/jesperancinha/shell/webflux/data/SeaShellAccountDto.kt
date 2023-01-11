package org.jesperancinha.shell.webflux.data

import lombok.*
import java.math.BigDecimal

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
class SeaShellAccountDto {
    private val value: BigDecimal? = null
    private val currency: String? = null
}