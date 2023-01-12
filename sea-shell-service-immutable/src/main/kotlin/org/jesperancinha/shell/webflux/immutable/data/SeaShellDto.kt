package org.jesperancinha.shell.webflux.immutable.data

import lombok.Builder

@JvmRecord
data class SeaShellDto @Builder constructor(
    val name: String,
    val scientificName: String,
    val slogan: String,
    val persons: List<SeaShellPersonDto>,
    val costumes: List<SeaShellCostumeDto>,
    val personIds: List<Long>,
    val costumeIds: List<Long>
)