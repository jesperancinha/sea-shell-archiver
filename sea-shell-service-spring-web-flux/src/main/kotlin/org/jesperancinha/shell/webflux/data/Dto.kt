package org.jesperancinha.shell.webflux.data

import lombok.Builder
import java.math.BigDecimal


data class SeaShellAccountDto(
    private val value: BigDecimal? = null,
    private val currency: String? = null
)

data class SeaShellCostumeDto(
    private val topDto: SeaShellTopDto? = null,
    private val lowerDto: SeaShellLowerDto? = null,
    private val topId: Long? = null,
    private val lowerId: Long? = null
)

data class SeaShellDto @Builder constructor(
    val name: String,
    val scientificName: String,
    val slogan: String,
    val persons: MutableList<SeaShellPersonDto?>,
    val costumes: MutableList<SeaShellCostumeDto?>,
    val personIds: List<Long>,
    val costumeIds: List<Long>
) {
    fun addPersons(persons: List<SeaShellPersonDto?>?) {
        this.persons.addAll(persons!!)
    }

    fun addCostumes(costumes: List<SeaShellCostumeDto?>?) {
        this.costumes.addAll(costumes!!)
    }
}

data class SeaShellLowerDto(
    private val type: String? = null,
    private val color: String? = null,
    private val size: String? = null
)

data class SeaShellPersonDto(
    private val name: String? = null,
    private val activity: String? = null,
    private val costumeDto: SeaShellCostumeDto? = null,
    private val accountDto: SeaShellAccountDto? = null,

    @Transient
    private val costumeId: Long? = null,

    @Transient
    private val accountId: String? = null
)

data class SeaShellTopDto(
    private val type: String? = null,
    private val color: String? = null,
    private val size: String? = null
)