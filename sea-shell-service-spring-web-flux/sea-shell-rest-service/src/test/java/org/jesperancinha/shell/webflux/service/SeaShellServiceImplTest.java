package org.jesperancinha.shell.webflux.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.jesperancinha.shell.SeaShellWiremockSoapLauncher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.blockhound.BlockHound;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static reactor.core.publisher.Mono.delay;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SeaShellServiceImplTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private SeaShellService seaShellService;

    @BeforeAll
    public static void setUpAll() throws IOException {
        BlockHound.install();
        wireMockServer = SeaShellWiremockSoapLauncher.createWireMockServer();
    }

    @Test
    public void findAllCompleteSeaShells_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShells())
                .block());
    }

    @Test
    @Disabled
    public void findAllCompleteSeaShellsBlock_onCall_thenBlocking() {
        assertThrows(Exception.class, () -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShellsNaifBlock())
                .block());
    }

    @Test
    public void findAllCompleteSeaShellsReactiveBlock_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShellsReactiveBlock())
                .block());
    }

    @Test
    public void findAllCompleteSeaShellsReactiveWithDelay_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShellsReactiveWithDelay())
                .block());
    }

    @Test
    public void findAllCompleteSeaShellsReactiveWithForkJoins_onCall_thenBlocking() {
        assertThrows(Exception.class, () -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShellsReactiveWithForkJoins())
                .block());
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }

}