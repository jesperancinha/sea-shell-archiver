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
class SeaShellReactiveOneServiceImplTest {
    @Autowired
    private val seaShellReactiveOneService: SeaShellReactiveOneServiceImpl? = null
    @Test
    fun findAllIds_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellReactiveOneService!!.allIds }
                .block()
        })
    }

    @Test
    fun findSeaShellById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellReactiveOneService!!.getSeaShellById(1L) }
                .block()
        })
    }

    @Test
    fun findPersonById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellReactiveOneService!!.getPersonById(1L) }
                .block()
        })
    }

    @Test
    fun findCostumeById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellReactiveOneService!!.getCostumeById(1L) }
                .block()
        })
    }

    @Test
    fun findAccountById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellReactiveOneService!!.getAccountById("ACCOUNTID") }
                .block()
        })
    }

    @Test
    fun findTopById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellReactiveOneService!!.getTopById(1L) }
                .block()
        })
    }

    @Test
    fun findLowerById_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellReactiveOneService!!.getLowerById(1L) }
                .block()
        })
    }

    companion object {
        private var wireMockServer: WireMockServer? = null
        @BeforeAll
        @Throws(IOException::class)
        fun setUpAll() {
            BlockHound.install()
            wireMockServer = SeaShellWiremockSoapLauncher.createWireMockServer()
        }

        @AfterAll
        fun afterAll() {
            wireMockServer!!.stop()
        }
    }
}