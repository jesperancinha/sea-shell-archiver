package org.jesperancinha.shell.webflux.immutable.data

import lombok.Builder

@JvmRecord
data class SeaShellPersonDto @Builder constructor(
    val name: String,
    val activity: String,
    val costumeDto: SeaShellCostumeDto,
    val accountDto: SeaShellAccountDto,
    val costumeId: Long,
    val accountId: String
) {
    init {
        name = name
    }
}