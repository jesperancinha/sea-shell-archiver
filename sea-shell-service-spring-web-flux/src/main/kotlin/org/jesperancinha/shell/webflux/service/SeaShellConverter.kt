package org.jesperancinha.shell.webflux.service

import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.webflux.data.*

object SeaShellConverter {
    fun toLowerDto(lower: Lower?): SeaShellLowerDto {
        return SeaShellLowerDto.builder()
            .color(lower!!.color)
            .size(lower.size)
            .type(lower.type)
            .build()
    }

    fun toTopDto(top: Top?): SeaShellTopDto {
        return SeaShellTopDto.builder()
            .color(top!!.color)
            .size(top.size)
            .type(top.type)
            .build()
    }

    fun toAccountDto(accountById: Account?): SeaShellAccountDto {
        return SeaShellAccountDto.builder()
            .currency(accountById!!.currency)
            .value(accountById.value)
            .build()
    }

    fun toShellPersonDto(person: Person?): SeaShellPersonDto {
        return SeaShellPersonDto.builder()
            .name(person!!.name)
            .accountDto(SeaShellAccountDto())
            .activity(person.activity)
            .costumeId(person.costumeId)
            .accountId(person.accountId)
            .build()
    }

    fun toShellCostumeDto(costume: Costume?): SeaShellCostumeDto {
        return SeaShellCostumeDto.builder()
            .topDto(SeaShellTopDto())
            .lowerDto(SeaShellLowerDto())
            .topId(costume!!.topId)
            .lowerId(costume.lowerId)
            .build()
    }

    fun toShellDto(shell: Shell?): SeaShellDto {
        return SeaShellDto.builder()
            .name(shell!!.name)
            .scientificName(shell.scientificName)
            .slogan(shell.slogan)
            .personIds(shell.persons.personId)
            .costumeIds(shell.costumes.costumeId)
            .costumes(ArrayList())
            .persons(ArrayList())
            .build()
    }
}