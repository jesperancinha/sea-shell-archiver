package org.jesperancinha.ssa.webflux.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class SeaShellWebClientTest {

    @Test
    public void consume() throws InterruptedException {
        final SeaShellWebClient seaShellWebClient = new SeaShellWebClient();
        assertAll(seaShellWebClient::consume);
    }
}