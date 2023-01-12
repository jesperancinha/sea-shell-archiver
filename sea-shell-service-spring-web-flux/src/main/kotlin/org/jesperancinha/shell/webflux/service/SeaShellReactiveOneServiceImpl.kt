package org.jesperancinha.shell.webflux.service

import lombok.extern.slf4j.Slf4j
import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.shells.Shell
import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.webflux.data.*
import org.jesperancinha.shell.webflux.repo.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Slf4j
@Service
class SeaShellReactiveOneServiceImpl(
    shellRepository: ShellRepositoryImpl,
    shellPersonRepository: ShellPersonRepositoryImpl,
    shellCostumeRepository: ShellCostumeRepositoryImpl,
    shellAccountRepository: ShellAccountRepositoryImpl,
    shellTopRepository: ShellTopRepositoryImpl,
    shellLowerRepository: ShellLowerRepositoryImpl
) : SeaShellOneAdapter(
    shellRepository,
    shellPersonRepository,
    shellCostumeRepository,
    shellAccountRepository,
    shellTopRepository,
    shellLowerRepository
) {
    fun allIds(): Flux<Long> = shellRepository.findAllShellIds()

    fun getSeaShellById(id: Long): Mono<SeaShellDto> {
        return shellRepository.findSeaShellById(id)
            .map { SeaShellConverter.toShellDto(it) }
    }

    fun getPersonById(id: Long): Mono<SeaShellPersonDto> {
        return shellPersonRepository.findPersonById(id)
            .map { SeaShellConverter.toShellPersonDto(it) }
    }

    fun getCostumeById(id: Long): Mono<SeaShellCostumeDto> {
        return shellCostumeRepository.findCostumeById(id)
            .map { SeaShellConverter.toShellCostumeDto(it) }
    }

    fun getAccountById(id: String): Mono<SeaShellAccountDto> {
        return shellAccountRepository.findAccountById(id)
            .map { SeaShellConverter.toAccountDto(it) }
    }

    fun getTopById(id: Long): Mono<SeaShellTopDto> {
        return shellTopRepository.findTopById(id)
            .map { SeaShellConverter.toTopDto(it) }
    }

    fun getLowerById(id: Long): Mono<SeaShellLowerDto> {
        return shellLowerRepository.findLowerById(id)
            .map { SeaShellConverter.toLowerDto(it) }
    }
}