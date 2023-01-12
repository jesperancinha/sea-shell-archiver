package org.jesperancinha.shell.webflux.service

import lombok.extern.slf4j.Slf4j
import org.jesperancinha.shell.client.accounts.Account
import org.jesperancinha.shell.client.costumes.Costume
import org.jesperancinha.shell.client.lowers.Lower
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.client.tops.Top
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.data.SeaShellDto
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.function.Consumer
import java.util.stream.Collectors

@Slf4j
open class SeaShellConsumerAdapter(
    protected val costumeRepository: ShellCostumeRepositoryImpl,
    protected val personRepository: ShellPersonRepositoryImpl,
    protected val accountRepository: ShellAccountRepositoryImpl,
    protected val topRepository: ShellTopRepositoryImpl,
    protected val lowerRepository: ShellLowerRepositoryImpl
) {
    protected fun consumerPersons(): Consumer<SeaShellDto> {
        return Consumer { seaShellDto: SeaShellDto ->
            personRepository
                .findPersons(seaShellDto.personIds)
                .map { person -> SeaShellConverter.toShellPersonDto(person) }
                .doOnNext { personDto: SeaShellPersonDto? ->
                    seaShellDto
                        .persons
                        .add(personDto)
                }
                .subscribe { seaShellPersonDto ->
                    seaShellPersonDto.costumeId?.let {
                        costumeRepository.findCostumeById(it)
                            .subscribe { costume ->
                                val newSeaShellPersonDto = seaShellPersonDto.copy(
                                    costumeDto = SeaShellConverter.toShellCostumeDto(costume)
                                )
                                val costumeDto = newSeaShellPersonDto.costumeDto

                                if (costumeDto != null) {
                                    consumerCostume().accept(costumeDto)
                                    logger.info("Complete costume before calling sub top/lower threads ->$costumeDto")
                                }
                            }
                    }
                    seaShellPersonDto.accountId?.let {
                        accountRepository.findAccountById(it)
                            .subscribe { account ->
                                seaShellPersonDto.copy(accountDto = SeaShellConverter.toAccountDto(account))
                                    .also {
                                        logger.info("Complete account after calling account thread ->" + seaShellPersonDto.accountDto)
                                    }
                            }
                    }
                }
        }
    }

    protected fun consumerCostumes(): Consumer<SeaShellDto> {
        return Consumer { seaShellDto: SeaShellDto ->
            costumeRepository
                .findCostumes(seaShellDto.costumeIds)
                .map { costume -> SeaShellConverter.toShellCostumeDto(costume) }
                .doOnNext { seaShellCostumeDto: SeaShellCostumeDto? ->
                    seaShellDto
                        .costumes
                        .add(seaShellCostumeDto)
                }
                .subscribe(consumerCostume())
        }
    }

    private fun consumerCostume(): Consumer<SeaShellCostumeDto> {
        return Consumer { seaShellCostumeDto ->
            topRepository.findTopById(seaShellCostumeDto.topId)
                .subscribe { top ->
                    seaShellCostumeDto.copy(
                        topDto = SeaShellConverter.toTopDto(top)
                    )
                        .also {
                            logger.info("Complete costume after calling sub top thread ->$it")
                        }
                }
            lowerRepository.findLowerById(seaShellCostumeDto.lowerId)
                .subscribe { lower ->
                    seaShellCostumeDto.copy(
                        lowerDto = SeaShellConverter.toLowerDto(lower)
                    )
                        .also {
                            logger.info("Complete costume after calling sub top thread ->$seaShellCostumeDto")
                        }
                }
        }
    }

    protected fun setMainRootElements(seaShellDto: SeaShellDto) {
        seaShellDto.addCostumes(
            costumeRepository
                .findCostumesBlock(seaShellDto.costumeIds)
                .parallelStream()
                .map { costume ->
                    SeaShellConverter
                        .toShellCostumeDto(costume)
                        .setCostumeRootElements()
                }
                .collect(Collectors.toList()))
        seaShellDto.addPersons(
            personRepository
                .findPersonsBlock(seaShellDto.personIds)
                .map { person ->
                    SeaShellConverter.toShellPersonDto(person)
                        .let {
                            it.copy(
                                accountDto = it.accountId
                                    ?.let { accountId -> accountRepository.findAccountByIdBlock(accountId) }
                                    ?.let { account -> SeaShellConverter.toAccountDto(account) })
                        }
                        .let { seaShellPersonDto ->
                            seaShellPersonDto
                                .copy(
                                    costumeDto =
                                    seaShellPersonDto.costumeId
                                        ?.let { costumeRepository.findCostumeByIdBlock(it) }
                                        ?.let {
                                            SeaShellConverter.toShellCostumeDto(it)
                                        })
                        }
                        .let { seaShellPersonDto ->
                            seaShellPersonDto.costumeDto.let {
                                if (it != null) {
                                    seaShellPersonDto.setCostumeRootElements(it)
                                } else null
                            }
                        }
                })
    }

    private fun SeaShellPersonDto.setCostumeRootElements(seaShellCostumeDto: SeaShellCostumeDto) =
        seaShellCostumeDto.setCostumeRootElements()
            .let { costumeDto -> this.copy(costumeDto = costumeDto) }

    private fun SeaShellCostumeDto.setCostumeRootElements() =
        this.copy(
            topDto = this.topId?.let { topRepository.findTopByIdBlock(it) }?.let { SeaShellConverter.toTopDto(it) },
            lowerDto = this.lowerId?.let { SeaShellConverter.toLowerDto(lowerRepository.findLowerByIdBlock(this.lowerId)) }
        )

    companion object {
        val logger: Logger = LoggerFactory.getLogger(SeaShellConsumerAdapter::class.java)
    }
}