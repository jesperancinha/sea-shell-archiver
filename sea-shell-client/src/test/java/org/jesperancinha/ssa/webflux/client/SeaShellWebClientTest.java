package org.jesperancinha.ssa.webflux.client;

import org.junit.jupiter.api.Test;

class SeaShellWebClientTest
{

    @Test
    void consume()
    {
        final SeaShellWebClient seaShellWebClient = new SeaShellWebClient();

        seaShellWebClient.consume();
    }
}