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
class SeaShellPersonsRecursiveTask : RecursiveTask<Stream<ForkJoinTask<SeaShellPersonDto?>?>>() {
    private val personRepository: ShellPersonRepositoryImpl? = null
    private val accountRepository: ShellAccountRepositoryImpl? = null
    private val costumeRepository: ShellCostumeRepositoryImpl? = null
    private val topRepository: ShellTopRepositoryImpl? = null
    private val lowerRepository: ShellLowerRepositoryImpl? = null
    private val seaShellDto: SeaShellDto? = null
    private val commonPool: ForkJoinPool? = null
    override fun compute(): Stream<ForkJoinTask<SeaShellPersonDto?>?> {
        val personsBlock = personRepository!!.findPersonsBlock(seaShellDto!!.personIds)
        return personsBlock!!.parallelStream().map { obj: Person? -> SeaShellConverter.toShellPersonDto() }
            .flatMap { seaShellPersonDto: SeaShellPersonDto? ->
                getSeaShellPersonForkJoinTaskStream(
                    seaShellPersonDto,
                    commonPool
                )
            }
    }

    private fun getSeaShellPersonForkJoinTaskStream(
        seaShellPersonDto: SeaShellPersonDto?, commonPool: ForkJoinPool?
    ): Stream<ForkJoinTask<SeaShellPersonDto?>?> {
        return Stream.of(
            commonPool.submit(
                SeaShellPersonAccountRecursiveTask.builder().accountRepository(accountRepository)
                    .seaShellPersonDto(seaShellPersonDto).build()
            ),
            commonPool.submit(
                SeaShellCostumeRecursiveTask.builder().costumeRepository(costumeRepository)
                    .topRepository(topRepository)
                    .lowerRepository(lowerRepository)
                    .seaShellPersonDto(seaShellPersonDto)
                    .commonPool(commonPool)
                    .build()
            )
        )
    }
}