package org.jesperancinha.shell.webflux.immutable.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.jesperancinha.shell.SeaShellWiremockSoapLauncher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.blockhound.BlockHound;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static reactor.core.publisher.Mono.delay;

@SpringBootTest
public class SeaShellsReactiveImmutableServiceTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private SeaShellsReactiveImmutableService seaShellService;

    @BeforeAll
    public static void setUpAll() throws IOException {
        BlockHound.install();
        wireMockServer = SeaShellWiremockSoapLauncher.createWireMockServer();
    }

    @Test
    public void findAllCompleteSeaShells_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllShells())
                .block());
    }
    @Test
    public void findAllCompleteSeaShellById1s_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getSeaShellById(1L))
                .block());
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }
}
