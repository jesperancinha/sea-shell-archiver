package org.jesperancinha.shell.webflux.immutable.data

data class SeaShellCostumeDto (
    val topDto: SeaShellTopDto,
    val lowerDto: SeaShellLowerDto,
    val topId: Long,
    val lowerId: Long
)