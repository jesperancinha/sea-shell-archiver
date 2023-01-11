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
                .map { obj: Person? -> SeaShellConverter.toShellPersonDto() }
                .doOnNext { personDto: SeaShellPersonDto? ->
                    seaShellDto
                        .persons
                        .add(personDto)
                }
                .subscribe { seaShellPersonDto: SeaShellPersonDto? ->
                    costumeRepository.findCostumeById(seaShellPersonDto.getCostumeId())
                        .subscribe { costume: Costume? ->
                            seaShellPersonDto.setCostumeDto(SeaShellConverter.toShellCostumeDto(costume))
                            val costumeDto = seaShellPersonDto.getCostumeDto()
                            consumerCostume().accept(costumeDto)
                            SeaShellConsumerAdapter.log.info("Complete costume before calling sub top/lower threads ->$costumeDto")
                        }
                    accountRepository.findAccountById(seaShellPersonDto.getAccountId())
                        .subscribe { account: Account? ->
                            seaShellPersonDto.setAccountDto(SeaShellConverter.toAccountDto(account))
                            SeaShellConsumerAdapter.log.info("Complete account after calling account thread ->" + seaShellPersonDto.getAccountDto())
                        }
                }
        }
    }

    protected fun consumerCostumes(): Consumer<SeaShellDto> {
        return Consumer { seaShellDto: SeaShellDto ->
            costumeRepository
                .findCostumes(seaShellDto.costumeIds)
                .map { obj: Costume? -> SeaShellConverter.toShellCostumeDto() }
                .doOnNext { seaShellCostumeDto: SeaShellCostumeDto? ->
                    seaShellDto
                        .costumes
                        .add(seaShellCostumeDto)
                }
                .subscribe(consumerCostume())
        }
    }

    private fun consumerCostume(): Consumer<SeaShellCostumeDto?> {
        return Consumer { seaShellCostumeDto: SeaShellCostumeDto? ->
            topRepository.findTopById(seaShellCostumeDto.getTopId())
                .subscribe { top: Top? ->
                    seaShellCostumeDto.setTopDto(SeaShellConverter.toTopDto(top))
                    SeaShellConsumerAdapter.log.info("Complete costume after calling sub top thread ->$seaShellCostumeDto")
                }
            lowerRepository.findLowerById(seaShellCostumeDto.getLowerId())
                .subscribe { lower: Lower? ->
                    seaShellCostumeDto.setLowerDto(SeaShellConverter.toLowerDto(lower))
                    SeaShellConsumerAdapter.log.info("Complete costume after calling sub top thread ->$seaShellCostumeDto")
                }
        }
    }

    protected fun setMainRootElements(seaShellDto: SeaShellDto) {
        seaShellDto.addCostumes(
            costumeRepository
                .findCostumesBlock(seaShellDto.costumeIds)
                .parallelStream()
                .map { obj: Costume? -> SeaShellConverter.toShellCostumeDto() }
                .peek { seaShellCostumeDto: SeaShellCostumeDto? -> setCostumeRootElements(seaShellCostumeDto) }
                .collect(Collectors.toList()))
        seaShellDto.addPersons(
            personRepository
                .findPersonsBlock(seaShellDto.personIds)
                .parallelStream()
                .map { obj: Person? -> SeaShellConverter.toShellPersonDto() }
                .peek { seaShellPersonDto: SeaShellPersonDto? ->
                    seaShellPersonDto
                        .setAccountDto(
                            SeaShellConverter.toAccountDto(
                                accountRepository.findAccountByIdBlock(
                                    seaShellPersonDto.getAccountId()
                                )
                            )
                        )
                }
                .peek { seaShellPersonDto: SeaShellPersonDto? ->
                    seaShellPersonDto
                        .setCostumeDto(
                            SeaShellConverter.toShellCostumeDto(
                                costumeRepository.findCostumeByIdBlock(
                                    seaShellPersonDto.getCostumeId()
                                )
                            )
                        )
                }
                .peek { seaShellPersonDto: SeaShellPersonDto? -> setCostumeRootElements(seaShellPersonDto.getCostumeDto()) }
                .collect(Collectors.toList()))
    }

    private fun setCostumeRootElements(seaShellCostumeDto: SeaShellCostumeDto?) {
        seaShellCostumeDto.setTopDto(SeaShellConverter.toTopDto(topRepository.findTopByIdBlock(seaShellCostumeDto.getTopId())))
        seaShellCostumeDto.setLowerDto(
            SeaShellConverter.toLowerDto(
                lowerRepository.findLowerByIdBlock(
                    seaShellCostumeDto.getLowerId()
                )
            )
        )
    }
}