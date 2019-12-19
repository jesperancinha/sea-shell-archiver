package org.jesperancinha.shell;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToXml;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class SeaShellWiremockSoapLauncher {

    public static void main(String[] args) {
        final WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(post(urlEqualTo("/baeldung")).withRequestBody(equalToXml("<A></A>")).willReturn(aResponse().withBody("Welcome to Baeldung!")));

    }
}
