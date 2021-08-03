package org.jesperancinha.shell.webflux.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.jesperancinha.shell.SeaShellWiremockSoapLauncher;
import org.jesperancinha.shell.webflux.service.SeaShellReactiveServiceImpl;
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
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static reactor.core.publisher.Mono.delay;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Disabled
public class SeaShellReactiveServiceImplTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private SeaShellReactiveService seaShellReactiveService;

    @BeforeAll
    public static void setUpAll() throws IOException {
        BlockHound.install();
        wireMockServer = SeaShellWiremockSoapLauncher.createWireMockServer();
    }

    @Test
    public void findAllCompleteSeaShells_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveService.getAllSeaShells())
                .block());
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }
}