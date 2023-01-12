package org.jesperancinha.shell.webflux.service

import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.webflux.data.*

fun Lower.toLowerDto(): SeaShellLowerDto = SeaShellLowerDto(
    color = color,
    size = size,
    type = type
)

fun Top.toTopDto(): SeaShellTopDto = SeaShellTopDto(
    color = color,
    size = size,
    type = type
)

fun Account.toAccountDto() = SeaShellAccountDto(
    currency = currency,
    value = value
)

fun Person.toShellPersonDto() = SeaShellPersonDto(
    name = name,
    accountDto = SeaShellAccountDto(),
    activity = activity,
    costumeId = costumeId,
    accountId = accountId
)

fun Costume.toShellCostumeDto() = SeaShellCostumeDto(
    topDto = SeaShellTopDto(),
    lowerDto = SeaShellLowerDto(),
    topId = topId,
    lowerId = lowerId
)

fun Shell.toShellDto() = SeaShellDto(
        name = name,
        scientificName = scientificName,
        slogan = slogan,
        personIds = persons.personId,
        costumeIds = costumes.costumeId,
        costumes = mutableListOf(),
        persons = mutableListOf()
    )
