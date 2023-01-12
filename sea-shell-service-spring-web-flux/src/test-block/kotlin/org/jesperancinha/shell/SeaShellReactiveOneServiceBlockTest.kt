package org.jesperancinha.shell

import com.github.tomakehurst.wiremock.WireMockServer
import org.jesperancinha.shell.webflux.service.SeaShellReactiveOneServiceImpl
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
class SeaShellReactiveOneServiceBlockTest @Autowired constructor(
    @Autowired
    private val seaShellReactiveOneService: SeaShellReactiveOneServiceImpl
) {

    @Test
    fun findAllIds_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveOneService.allIds() }
                .block()
        })
    }

    @Test
    fun findSeaShellById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveOneService.getSeaShellById(1L) }
                .block()
        })
    }

    @Test
    fun findPersonById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveOneService.getPersonById(1L) }
                .block()
        })
    }

    @Test
    fun findCostumeById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveOneService.getCostumeById(1L) }
                .block()
        })
    }

    @Test
    fun findAccountById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveOneService.getAccountById("ACCOUNTID") }
                .block()
        })
    }

    @Test
    fun findTopById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveOneService.getTopById(1L) }
                .block()
        })
    }

    @Test
    fun findLowerById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { seaShellReactiveOneService.getLowerById(1L) }
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
        fun afterAll(): Unit = run { wireMockServer?.stop() }
    }
}