package org.jesperancinha.shell

import com.github.tomakehurst.wiremock.WireMockServer
import org.jesperancinha.shell.webflux.service.SeaShellReactiveService
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.blockhound.BlockHound
import reactor.core.publisher.Mono
import java.io.IOException
import java.time.Duration

@SpringBootTest
class SeaShellReactiveServiceTest @Autowired constructor(
    private val seaShellReactiveService: SeaShellReactiveService
) {

    @Test
    fun findAllCompleteSeaShells_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveService.allSeaShells() }
                .block()
        })
    }

    companion object {
        private var wireMockServer: WireMockServer? = null

        @JvmStatic
        @BeforeAll
        @Throws(IOException::class)
        fun setUpAll() {
            BlockHound.install()
            wireMockServer = SeaShellWiremockSoapLauncher.createWireMockServer()
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            wireMockServer?.stop()
        }
    }
}