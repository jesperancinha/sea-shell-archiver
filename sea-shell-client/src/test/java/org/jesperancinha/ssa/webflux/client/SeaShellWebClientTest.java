package org.jesperancinha.ssa.webflux.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;

public class SeaShellWebClientTest {

    private SeaShellWebClient seaShellWebClient;

    @BeforeEach
    public void setUp() {
        seaShellWebClient = new SeaShellWebClient();
    }

    @Test
    public void testConsume() throws InterruptedException {
        assertAll(seaShellWebClient::consume);
        Thread.sleep(5000);
    }

    @Test
    public void testGetSeaShellById() {
        assertAll(() -> Mono.delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellWebClient.getSeaShellById(1L))
                .block());
    }

    @Test
    public void testGetAllSeaShells() {
        assertAll(() -> Mono.delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellWebClient.getAllSeaShells())
                .block());
    }
}