package org.jesperancinha.ssa.webflux.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockResolver.Wiremock;
import ru.lanwen.wiremock.ext.WiremockUriResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver.WiremockUri;

import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static reactor.core.publisher.Mono.delay;

@ExtendWith({
        WiremockResolver.class,
        WiremockUriResolver.class
})
public class SeaShellWebClientTest {

    @Test
    public void testConsume(@Wiremock WireMockServer server, @WiremockUri String uri) throws InterruptedException {
        server.stubFor(get(urlEqualTo("/seashells/1")).willReturn(aResponse().withBody("{\"id\":null,\"commonName\":\"ugabuga\",\"scientificName\":\"ugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null}")));
        server.stubFor(get(urlEqualTo("/seashells")).willReturn(aResponse().withBody("[{\"id\":null,\"commonName\":\"ugabuga\",\"scientificName\":\"ugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null},{\"id\":null,\"commonName\":\"bagabuga\",\"scientificName\":\"bugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null}]")));
        SeaShellWebClient seaShellWebClient = new SeaShellWebClient(uri);

        assertAll(seaShellWebClient::consume);
        Thread.sleep(5000);
    }

    @Test
    public void testGetSeaShellById(@Wiremock WireMockServer server, @WiremockUri String uri) {
        server.stubFor(get(urlEqualTo("/seashells/1")).willReturn(aResponse().withBody("{\"id\":null,\"commonName\":\"ugabuga\",\"scientificName\":\"ugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null}")));
        server.stubFor(get(urlEqualTo("/seashells")).willReturn(aResponse().withBody("[{\"id\":null,\"commonName\":\"ugabuga\",\"scientificName\":\"ugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null},{\"id\":null,\"commonName\":\"bagabuga\",\"scientificName\":\"bugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null}]")));
        SeaShellWebClient seaShellWebClient = new SeaShellWebClient(uri);
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellWebClient.getSeaShellById(1L))
                .block());
    }

    @Test
    public void testGetAllSeaShells(@Wiremock WireMockServer server, @WiremockUri String uri) {
        server.stubFor(get(urlEqualTo("/seashells/1")).willReturn(aResponse().withBody("{\"id\":null,\"commonName\":\"ugabuga\",\"scientificName\":\"ugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null}")));
        server.stubFor(get(urlEqualTo("/seashells")).willReturn(aResponse().withBody("[{\"id\":null,\"commonName\":\"ugabuga\",\"scientificName\":\"ugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null},{\"id\":null,\"commonName\":\"bagabuga\",\"scientificName\":\"bugalibis\",\"currency\":\"EUR\",\"value\":100,\"shellState\":\"Optimal\",\"shellType\":\"Bivalvia\",\"seaShellLocationListIds\":null}]")));
        SeaShellWebClient seaShellWebClient = new SeaShellWebClient(uri);
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellWebClient.getAllSeaShells())
                .block());
    }
}