package org.jesperancinha.shell.webflux.data

import java.math.BigDecimal


data class SeaShellAccountDto(
    val value: BigDecimal? = null,
    val currency: String? = null
)

data class SeaShellCostumeDto(
    val topDto: SeaShellTopDto? = null,
    val lowerDto: SeaShellLowerDto? = null,
    val topId: Long? = null,
    val lowerId: Long? = null
)

data class SeaShellDto(
    val name: String,
    val scientificName: String,
    val slogan: String,
    val persons: MutableList<SeaShellPersonDto?>,
    val costumes: MutableList<SeaShellCostumeDto?>,
    val personIds: List<Long>,
    val costumeIds: List<Long>
) {
    fun addPersons(persons: List<SeaShellPersonDto>) = this.persons.addAll(persons)

    fun addCostumes(costumes: List<SeaShellCostumeDto>) = this.costumes.addAll(costumes)
}

data class SeaShellLowerDto(
    val type: String? = null,
    val color: String? = null,
    val size: String? = null
)

data class SeaShellPersonDto(
    val name: String? = null,
    val activity: String? = null,
    val costumeDto: SeaShellCostumeDto? = null,
    val accountDto: SeaShellAccountDto? = null,

    @Transient
    val costumeId: Long? = null,

    @Transient
    val accountId: String? = null
)

data class SeaShellTopDto(
    val type: String? = null,
    val color: String? = null,
    val size: String? = null
)