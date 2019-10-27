package org.jesperancinha.ssa.webflux.client;

import org.junit.jupiter.api.Test;

class SeaShellWebClientTest {

    @Test
    void consume() throws InterruptedException {
        final SeaShellWebClient seaShellWebClient = new SeaShellWebClient();

        seaShellWebClient.consume();

        Thread.sleep(10000);
    }
}