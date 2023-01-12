package org.jesperancinha.shell

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldNotBeNull
import org.jesperancinha.shell.webflux.service.SeaShellService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.ws.client.WebServiceIOException

@SpringBootTest
class SeaShellServiceTest @Autowired constructor(
    private val seaShellService: SeaShellService
) {

    @Test
    fun `should return a non null flux of shells`() {
        seaShellService.allSeaShells().shouldNotBeNull()
    }

    @Test
    fun `should throw exception with direct access and no legacy service starting `() {
        shouldThrow<WebServiceIOException> {
            seaShellService.allSeaShellsNaifBlock()
        }
    }

    @Test
    fun `should return a non null parallel flux of shells`() {
        seaShellService.allSeaShellsReactiveBlock().shouldNotBeNull()
    }

    @Test
    fun `should return a non null flux of shells with a delay`() {
        seaShellService.allSeaShellsReactiveWithDelay().shouldNotBeNull()
    }

    @Test
    fun `should throw an exception for a non null flux of shells started with forked joins`() {
        shouldThrow<WebServiceIOException> {
            seaShellService.allSeaShellsReactiveWithForkJoins().shouldNotBeNull()
        }
    }
}