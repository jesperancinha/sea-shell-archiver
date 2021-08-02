package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.client.accounts.Account;
import org.jesperancinha.shell.client.costumes.Costume;
import org.jesperancinha.shell.client.lowers.Lower;
import org.jesperancinha.shell.client.persons.Person;
import org.jesperancinha.shell.client.shells.Shell;
import org.jesperancinha.shell.client.tops.Top;
import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;

import java.util.ArrayList;

public class SeaShellConverter {
    public static SeaShellLowerDto toLowerDto(Lower lower) {
        return SeaShellLowerDto.builder()
                .color(lower.getColor())
                .size(lower.getSize())
                .type(lower.getType())
                .build();
    }

    public static SeaShellTopDto toTopDto(Top top) {
        return SeaShellTopDto.builder()
                .color(top.getColor())
                .size(top.getSize())
                .type(top.getType())
                .build();
    }

    public static SeaShellAccountDto toAccountDto(Account accountById) {
        return SeaShellAccountDto.builder()
                .currency(accountById.getCurrency())
                .value(accountById.getValue())
                .build();
    }

    public static SeaShellPersonDto toShellPersonDto(Person person) {
        return SeaShellPersonDto.builder()
                .name(person.getName())
                .accountDto(new SeaShellAccountDto())
                .activity(person.getActivity())
                .costumeId(person.getCostumeId())
                .accountId(person.getAccountId())
                .build();
    }

    public static SeaShellCostumeDto toShellCostumeDto(final Costume costume) {
        return SeaShellCostumeDto.builder()
                .topDto(new SeaShellTopDto())
                .lowerDto(new SeaShellLowerDto())
                .topId(costume.getTopId())
                .lowerId(costume.getLowerId())
                .build();
    }

    public static SeaShellDto toShellDto(Shell shell) {
        return SeaShellDto.builder()
                .name(shell.getName())
                .scientificName(shell.getScientificName())
                .slogan(shell.getSlogan())
                .personIds(shell.getPersons().getPersonId())
                .costumeIds(shell.getCostumes   ().getCostumeId())
                .costumes(new ArrayList<>())
                .persons(new ArrayList<>())
                .build();
    }
}
