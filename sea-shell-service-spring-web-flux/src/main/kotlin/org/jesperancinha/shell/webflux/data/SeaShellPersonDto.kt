package org.jesperancinha.shell.webflux.data

import lombok.*

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
class SeaShellPersonDto {
    private val name: String? = null
    private val activity: String? = null
    private val costumeDto: SeaShellCostumeDto? = null
    private val accountDto: SeaShellAccountDto? = null

    @Transient
    private val costumeId: Long? = null

    @Transient
    private val accountId: String? = null
}