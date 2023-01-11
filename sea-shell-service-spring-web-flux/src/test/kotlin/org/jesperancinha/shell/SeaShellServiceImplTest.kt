package org.jesperancinha.shell

import com.github.tomakehurst.wiremock.WireMockServer
import org.jesperancinha.shell.webflux.service.SeaShellServiceImpl
import org.junit.jupiter.api.*
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.blockhound.BlockHound
import reactor.core.publisher.Mono
import java.io.IOException
import java.time.Duration

@SpringBootTest
class SeaShellServiceImplTest {
    @Autowired
    private val seaShellService: SeaShellServiceImpl? = null
    @Test
    fun findAllCompleteSeaShells_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellService!!.allSeaShells }
                .block()
        })
    }

    @Test
    @Disabled
    fun findAllCompleteSeaShellsBlock_onCall_thenBlocking() {
        Assertions.assertThrows(Exception::class.java) {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellService!!.allSeaShellsNaifBlock }
                .block()
        }
    }

    @Test
    fun findAllCompleteSeaShellsReactiveBlock_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellService!!.allSeaShellsReactiveBlock }
                .block()
        })
    }

    @Test
    fun findAllCompleteSeaShellsReactiveWithDelay_onCall_thenNonBlocking() {
        Assertions.assertAll(Executable {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellService!!.allSeaShellsReactiveWithDelay }
                .block()
        })
    }

    @Test
    fun findAllCompleteSeaShellsReactiveWithForkJoins_onCall_thenBlocking() {
        Assertions.assertThrows(Exception::class.java) {
            Mono.delay(Duration.ofSeconds(1))
                .doOnNext { it: Long? -> seaShellService!!.allSeaShellsReactiveWithForkJoins }
                .block()
        }
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