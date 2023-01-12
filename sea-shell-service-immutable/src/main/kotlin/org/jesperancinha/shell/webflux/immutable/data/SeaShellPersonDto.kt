package org.jesperancinha.shell.webflux.immutable.data

data class SeaShellPersonDto(
    val name: String,
    val activity: String? = null,
    val costumeDto: SeaShellCostumeDto,
    val accountDto: SeaShellAccountDto,
    val costumeId: Long,
    val accountId: String
)