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
    val allIds: Flux<Long?>?
        get() = shellRepository.findAllShellIds()

    fun getSeaShellById(id: Long?): Mono<SeaShellDto?> {
        return shellRepository.findSeaShellById(id)
            .map { obj: Shell? -> SeaShellConverter.toShellDto() }
    }

    fun getPersonById(id: Long?): Mono<SeaShellPersonDto?> {
        return shellPersonRepository.findPersonById(id)
            .map { obj: Person? -> SeaShellConverter.toShellPersonDto() }
    }

    fun getCostumeById(id: Long?): Mono<SeaShellCostumeDto?> {
        return shellCostumeRepository.findCostumeById(id)
            .map { obj: Costume? -> SeaShellConverter.toShellCostumeDto() }
    }

    fun getAccountById(id: String?): Mono<SeaShellAccountDto?> {
        return shellAccountRepository.findAccountById(id)
            .map { obj: Account? -> SeaShellConverter.toAccountDto() }
    }

    fun getTopById(id: Long?): Mono<SeaShellTopDto?> {
        return shellTopRepository.findTopById(id)
            .map { obj: Top? -> SeaShellConverter.toTopDto() }
    }

    fun getLowerById(id: Long?): Mono<SeaShellLowerDto?> {
        return shellLowerRepository.findLowerById(id)
            .map { obj: Lower? -> SeaShellConverter.toLowerDto() }
    }
}