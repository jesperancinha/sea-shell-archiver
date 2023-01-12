package org.jesperancinha.shell.webflux.service

import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto
import org.jesperancinha.shell.webflux.data.SeaShellDto
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.*
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask
import java.util.stream.Stream

class SeaShellCostumeRecursiveTask(
    private val costumeRepository: ShellCostumeRepository,
    private val topRepository: ShellTopRepository,
    private val lowerRepository: ShellLowerRepository,
    private val seaShellPersonDto: SeaShellPersonDto,
    private val commonPool: ForkJoinPool
) : SeaShelTopLowerAdapter<SeaShellPersonDto>() {

    override fun compute(): SeaShellPersonDto = seaShellPersonDto.copy(
        costumeDto = seaShellPersonDto.costumeId
            ?.let { costumeRepository.findCostumeByIdBlock(it) }
            ?.toShellCostumeDto()
    ).also { personDto ->
        val costumeDto = personDto.costumeDto
        val forkTopJoinTask = costumeDto?.let { getSeaShellCostumeTopForkJoinTask(topRepository, it, commonPool) }
        val forkLowerJoinTask = costumeDto?.let { getSeaShellCostumeLowerForkJoinTask(lowerRepository, it, commonPool) }
        forkTopJoinTask?.join()
        forkLowerJoinTask?.join()
    }
}

class SeaShellCostumesRecursiveTask(
    private val costumeRepository: ShellCostumeRepository,
    private val topRepository: ShellTopRepository,
    private val lowerRepository: ShellLowerRepository,
    private val seaShellDto: SeaShellDto,
    private val commonPool: ForkJoinPool
) : org.jesperancinha.shell.webflux.service.SeaShelTopLowerAdapter<Stream<ForkJoinTask<SeaShellCostumeDto>>>() {

    override fun compute(): Stream<ForkJoinTask<SeaShellCostumeDto>> {
        val costumesBlock = costumeRepository.findCostumesBlock(seaShellDto.costumeIds)
        return costumesBlock.map { costume -> costume.toShellCostumeDto() }
            .stream().flatMap { seaShellCostumeDto ->
                Stream.of(
                    getSeaShellCostumeTopForkJoinTask(
                        topRepository = topRepository, costumeDto = seaShellCostumeDto, commonPool = commonPool
                    ),
                    getSeaShellCostumeLowerForkJoinTask(
                        lowerRepository = lowerRepository, costumeDto = seaShellCostumeDto, commonPool = commonPool
                    )
                )
            }
    }
}

class SeaShellLowerRecursiveTask(
    private val lowerRepository: ShellLowerRepository,
    private val costumeDto: SeaShellCostumeDto
) : RecursiveTask<SeaShellCostumeDto>() {

    override fun compute(): SeaShellCostumeDto =
        costumeDto.copy(lowerDto = costumeDto.lowerId
            ?.let { lowerRepository.findLowerByIdBlock(it) }
            ?.toLowerDto()
        )
}

class SeaShellPersonAccountRecursiveTask(
    private val accountRepository: ShellAccountRepository,
    private val seaShellPersonDto: SeaShellPersonDto
) : RecursiveTask<SeaShellPersonDto>() {

    override fun compute(): SeaShellPersonDto = seaShellPersonDto.copy(
        accountDto = seaShellPersonDto.accountId
            ?.let { accountRepository.findAccountByIdBlock(it) }
            ?.toAccountDto()
    )
}

class SeaShellPersonsRecursiveTask(
    private val personRepository: ShellPersonRepository,
    private val accountRepository: ShellAccountRepository,
    private val costumeRepository: ShellCostumeRepository,
    private val topRepository: ShellTopRepository,
    private val lowerRepository: ShellLowerRepository,
    private val seaShellDto: SeaShellDto,
    private val commonPool: ForkJoinPool
) : RecursiveTask<Stream<ForkJoinTask<SeaShellPersonDto>>>() {

    override fun compute(): Stream<ForkJoinTask<SeaShellPersonDto>>? =
        personRepository.findPersonsBlock(seaShellDto.personIds)
            .map { person -> person.toShellPersonDto() }
            .stream().flatMap { seaShellPersonDto ->
                getSeaShellPersonForkJoinTaskStream(
                    seaShellPersonDto,
                    commonPool
                )
            }

    private fun getSeaShellPersonForkJoinTaskStream(
        seaShellPersonDto: SeaShellPersonDto, commonPool: ForkJoinPool
    ): Stream<ForkJoinTask<SeaShellPersonDto>> {
        return Stream.of(
            commonPool.submit(
                org.jesperancinha.shell.webflux.service.SeaShellPersonAccountRecursiveTask(
                    accountRepository = accountRepository,
                    seaShellPersonDto = seaShellPersonDto
                )
            ),
            commonPool.submit(
                org.jesperancinha.shell.webflux.service.SeaShellCostumeRecursiveTask(
                    costumeRepository = costumeRepository,
                    topRepository = topRepository,
                    lowerRepository = lowerRepository,
                    seaShellPersonDto = seaShellPersonDto,
                    commonPool = commonPool
                )
            )
        )
    }
}

class SeaShellTopRecursiveTask(
    val topRepository: ShellTopRepository,
    val costumeDto: SeaShellCostumeDto
) : RecursiveTask<SeaShellCostumeDto>() {

    override fun compute(): SeaShellCostumeDto =
        costumeDto.copy(topDto = costumeDto.topId?.let {
            topRepository.findTopByIdBlock(it)
        }?.toTopDto())
}

abstract class SeaShelTopLowerAdapter<T> : RecursiveTask<T>() {
    fun getSeaShellCostumeLowerForkJoinTask(
        lowerRepository: ShellLowerRepository, costumeDto: SeaShellCostumeDto, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> = commonPool.submit(
        SeaShellLowerRecursiveTask(
            lowerRepository = lowerRepository,
            costumeDto = costumeDto
        )
    )

    fun getSeaShellCostumeTopForkJoinTask(
        topRepository: ShellTopRepository, costumeDto: SeaShellCostumeDto, commonPool: ForkJoinPool
    ): ForkJoinTask<SeaShellCostumeDto> = commonPool.submit(
        SeaShellTopRecursiveTask(
            topRepository = topRepository,
            costumeDto = costumeDto
        )
    )
}

