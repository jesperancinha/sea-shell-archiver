package org.jesperancinha.shell.webflux.immutable.data

data class SeaShellDto(
    val name: String,
    val scientificName: String,
    val slogan: String? = null,
    val persons: List<SeaShellPersonDto>,
    val costumes: List<SeaShellCostumeDto>,
    val personIds: List<Long>,
    val costumeIds: List<Long>
)