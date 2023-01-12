package org.jesperancinha.shell.webflux.service.fork

import lombok.AllArgsConstructor
import lombok.Builder
import org.jesperancinha.shell.client.persons.Person
import org.jesperancinha.shell.webflux.data.SeaShellDto
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto
import org.jesperancinha.shell.webflux.repo.*
import org.jesperancinha.shell.webflux.service.SeaShellConverter
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveTask
import java.util.stream.Stream

@AllArgsConstructor
@Builder
class SeaShellPersonsRecursiveTask(
    private val personRepository: ShellPersonRepositoryImpl,
    private val accountRepository: ShellAccountRepositoryImpl,
    private val costumeRepository: ShellCostumeRepositoryImpl,
    private val topRepository: ShellTopRepositoryImpl,
    private val lowerRepository: ShellLowerRepositoryImpl,
    private val seaShellDto: SeaShellDto,
    private val commonPool: ForkJoinPool
) : RecursiveTask<Stream<ForkJoinTask<SeaShellPersonDto>>>() {

    override fun compute(): Stream<ForkJoinTask<SeaShellPersonDto>>? = personRepository.findPersonsBlock(seaShellDto.personIds)
            .map { person -> SeaShellConverter.toShellPersonDto(person) }
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
                SeaShellPersonAccountRecursiveTask(
                    accountRepository=accountRepository,
                    seaShellPersonDto= seaShellPersonDto
                )
            ),
            commonPool.submit(
                SeaShellCostumeRecursiveTask(
                    costumeRepository =costumeRepository,
                    topRepository = topRepository,
                    lowerRepository = lowerRepository,
                    seaShellPersonDto =seaShellPersonDto,
                    commonPool = commonPool
                )
            )
        )
    }
}