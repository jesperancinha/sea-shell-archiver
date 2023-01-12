package org.jesperancinha.shell.webflux.immutable.data

import lombok.Builder

@JvmRecord
data class SeaShellCostumeDto @Builder constructor(
    val topDto: SeaShellTopDto,
    val lowerDto: SeaShellLowerDto,
    val topId: Long,
    val lowerId: Long
)