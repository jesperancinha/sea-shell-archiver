package org.jesperancinha.shell.webflux.immutable.service

import io.mockk.MockKMatcherScope
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.AssertionsForClassTypes
import org.jesperancinha.shell.webflux.immutable.repository.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ExtendWith(MockKExtension::class)
internal class SeaShellsReactiveImmutableServiceUnitTest {
    @MockK(relaxed = true)
    lateinit var shellTopRepository: ShellTopImmutableRepository

    @MockK(relaxed = true)
    lateinit var shellLowerRepository: ShellLowerImmutableRepository

    @MockK(relaxed = true)
    lateinit var shellAccountImmutableRepository: ShellAccountImmutableRepository

    @MockK(relaxed = true)
    lateinit var shellCostumeImmutableRepository: ShellCostumeImmutableRepository

    @MockK(relaxed = true)
    lateinit var shellPersonImmutableRepository: ShellPersonImmutableRepository

    @MockK(relaxed = true)
    lateinit var shellImmutableRepository: ShellImmutableRepository

    @InjectMockKs
    lateinit var seaShellsReactiveImmutableService: SeaShellsReactiveImmutableService

    @Test
    fun testGetAllShells() {
        every { mockKMatcherScope: MockKMatcherScope? -> shellImmutableRepository!!.findAllShellIds() }.returns(
            Flux.from(
                Mono.empty()
            )
        )
        val seaShellDto = seaShellsReactiveImmutableService.allShells.blockFirst()!!
        AssertionsForClassTypes.assertThat(seaShellDto).isNull()
    }

    @Test
    fun testGetSeaShellById() {
    }

    @Test
    fun testGetPersonById() {
    }

    @Test
    fun testGetCostumeById() {
    }

    @Test
    fun testGetAccountById() {
    }

    @Test
    fun testGetTopById() {
    }

    @Test
    fun testGetLowerById() {
    }
}