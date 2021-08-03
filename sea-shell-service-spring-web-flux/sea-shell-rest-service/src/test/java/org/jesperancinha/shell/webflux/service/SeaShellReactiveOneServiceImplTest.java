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
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static reactor.core.publisher.Mono.delay;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SeaShellReactiveOneServiceImplTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private SeaShellReactiveOneService seaShellReactiveOneService;

    @BeforeAll
    public static void setUpAll() throws IOException {
        BlockHound.install();
        wireMockServer = SeaShellWiremockSoapLauncher.createWireMockServer();
    }

    @Test
    public void findAllIds_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveOneService.getAllIds())
                .block());
    }

    @Test
    public void findSeaShellById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveOneService.getSeaShellById(1L))
                .block());
    }

    @Test
    public void findPersonById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveOneService.getPersonById(1L))
                .block());
    }

    @Test
    public void findCostumeById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveOneService.getCostumeById(1L))
                .block());
    }

    @Test
    public void findAccountById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveOneService.getAccountById("ACCOUNTID"))
                .block());
    }

    @Test
    public void findTopById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveOneService.getTopById(1L))
                .block());
    }

    @Test
    public void findLowerById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveOneService.getLowerById(1L))
                .block());
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }
}