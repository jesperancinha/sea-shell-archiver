package org.jesperancinha.shell;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.ContentTypeHeader;
import com.google.common.io.CharStreams;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToXml;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class SeaShellWiremockSoapLauncher {

    public static void main(String[] args) throws IOException {
        final WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person1.xml", "/mock/responses/persons/person1.xml");
        stubRequestToResponse("/seashells/shells", "/mock/requests/shells/shell1.xml", "/mock/responses/shells/shell1.xml");
        stubRequestToResponse("/seashells/shells", "/mock/requests/shells/shell2.xml", "/mock/responses/shells/shell2.xml");
        stubRequestToResponse("/seashells/costumes", "/mock/requests/costumes/costume1.xml", "/mock/responses/costumes/costume1.xml");
        stubRequestToResponse("/seashells/lowers", "/mock/requests/lowers/lower1.xml", "/mock/responses/lowers/lower1.xml");
        stubRequestToResponse("/seashells/lowers", "/mock/requests/lowers/lower2.xml", "/mock/responses/lowers/lower2.xml");
        stubRequestToResponse("/seashells/tops", "/mock/requests/tops/top1.xml", "/mock/responses/tops/top1.xml");
        stubRequestToResponse("/seashells/tops", "/mock/requests/tops/top2.xml", "/mock/responses/tops/top2.xml");
        stubRequestToResponse("/seashells/accounts", "/mock/requests/accounts/account1.xml", "/mock/responses/accounts/account1.xml");
    }

    private static void stubRequestToResponse(String urlPath, String requestResource, String responseResource) throws IOException {
        stubFor(post(urlEqualTo(urlPath))
                .withRequestBody(equalToXml(CharStreams.toString(getStringFromResource(requestResource))))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static InputStreamReader getStringFromResource(String resourceName) {
        return new InputStreamReader(SeaShellWiremockSoapLauncher.class.getResourceAsStream(resourceName), Charset.defaultCharset());
    }
}
