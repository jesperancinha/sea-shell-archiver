package org.jesperancinha.shell.webflux.service

import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.webflux.data.*

object SeaShellConverter {
    fun toLowerDto(lower: Lower): SeaShellLowerDto = SeaShellLowerDto(
        color = lower.color,
        size = lower.size,
        type = lower.type
    )

    fun toTopDto(top: Top): SeaShellTopDto = SeaShellTopDto(
        color = top.color,
        size = top.size,
        type = top.type
    )

    fun toAccountDto(accountById: Account): SeaShellAccountDto = SeaShellAccountDto(
        currency = accountById.currency,
        value = accountById.value
    )

    fun toShellPersonDto(person: Person) = SeaShellPersonDto(
        name = person.name,
        accountDto = SeaShellAccountDto(),
        activity = person.activity,
        costumeId = person.costumeId,
        accountId = person.accountId
    )

    fun toShellCostumeDto(costume: Costume) = SeaShellCostumeDto(
        topDto = SeaShellTopDto(),
        lowerDto = SeaShellLowerDto(),
        topId = costume.topId,
        lowerId = costume.lowerId
    )

    fun toShellDto(shell: Shell): SeaShellDto {
        return SeaShellDto(
            name = shell.name,
            scientificName = shell.scientificName,
            slogan = shell.slogan,
            personIds = shell.persons.personId,
            costumeIds = shell.costumes.costumeId,
            costumes = mutableListOf(),
            persons = mutableListOf()
        )
    }
}
