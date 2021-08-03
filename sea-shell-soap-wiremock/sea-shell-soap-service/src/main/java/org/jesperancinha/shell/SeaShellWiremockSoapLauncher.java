package org.jesperancinha.shell;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.ContentTypeHeader;
import com.google.common.io.CharStreams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToXml;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingXPath;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class SeaShellWiremockSoapLauncher {
    private final static Log log = LogFactory.getLog(SeaShellWiremockSoapLauncher.class);

    public static void main(String[] args) throws IOException {
        log.info("Wiremock service starting...");
        createWireMockServer();
        log.info("Wiremock service started!");
    }

    public static WireMockServer createWireMockServer() throws IOException {
        final WireMockServer wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        configureFor("localhost", 8090);
        stubPersons();
        stubShells();
        stubCostumes();
        stubLowers();
        stubTops();
        stubAccounts();
        stupRequestToWSDL("/seashells/accounts?wsdl", "/mock/responses/accounts/SeaShellsWSDLAccounts.wsdl");
        stupRequestToWSDL("/seashells/costumes?wsdl", "/mock/responses/costumes/SeaShellsWSDLCostumes.wsdl");
        stupRequestToWSDL("/seashells/lowers?wsdl", "/mock/responses/lowers/SeaShellsWSDLLowers.wsdl");
        stupRequestToWSDL("/seashells/persons?wsdl", "/mock/responses/persons/SeaShellsWSDLPersons.wsdl");
        stupRequestToWSDL("/seashells/shells?wsdl", "/mock/responses/shells/SeaShellsWSDLShells.wsdl");
        stupRequestToWSDL("/seashells/tops?wsdl", "/mock/responses/tops/SeaShellsWSDLTops.wsdl");
        return wireMockServer;
    }

    private static void stubShells() throws IOException {
        for (int i = 1; i <= 16; i++) {
            stubRequestToShell("/mock/responses/shells/shell" + i + ".xml", i);
        }
        stubFor(post(urlEqualTo("/seashells/shells"))
                .withRequestBody(matchingXPath("/Envelope/Body/allShells"))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource("/mock/responses/shells/allShells.xml")))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));

    }

    private static void stubPersons() throws IOException {
        for (int i = 1; i <= 16; i++) {
            stubRequestToPerson("/mock/responses/persons/person" + i + ".xml", i);
        }
    }

    private static void stubCostumes() throws IOException {
        for (int i = 1; i <= 3; i++) {
            stubRequestToCostume("/mock/responses/costumes/costume" + i + ".xml", i);
        }
    }

    private static void stubLowers() throws IOException {
        for (int i = 1; i <= 2; i++) {
            stubRequestToLower("/mock/responses/lowers/lower" + i + ".xml", i);
        }
    }

    private static void stubTops() throws IOException {
        for (int i = 1; i <= 2; i++) {
            stubRequestToTop("/mock/responses/tops/top" + i + ".xml", i);
        }
    }

    private static void stubAccounts() throws IOException {
        for (int i = 1; i <= 1; i++) {
            stubRequestToAccount("/mock/responses/accounts/account" + i + ".xml", i);
        }
    }

    private static void stupRequestToWSDL(String urlString, String fileLocation) throws IOException {
        stubFor(get(urlEqualTo(urlString))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(fileLocation)))));
    }

    private static void stubRequestToResponse(String urlPath, String requestResource, String responseResource) throws IOException {
        stubFor(post(urlEqualTo(urlPath))
                .withRequestBody(equalToXml(CharStreams.toString(getStringFromResource(requestResource)), true))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static void stubRequestToShell(String responseResource, Integer i) throws IOException {
        stubFor(post(urlEqualTo("/seashells/shells"))
                .withRequestBody(matchingXPath("/Envelope/Body/shellRequest/shellId/text()", equalTo(i.toString())))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static void stubRequestToPerson(String responseResource, Integer i) throws IOException {
        stubFor(post(urlEqualTo("/seashells/persons"))
                .withRequestBody(matchingXPath("/Envelope/Body/personsRequest/personId/text()", equalTo(i.toString())))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static void stubRequestToCostume(String responseResource, Integer i) throws IOException {
        stubFor(post(urlEqualTo("/seashells/costumes"))
                .withRequestBody(matchingXPath("/Envelope/Body/costumesRequest/costumeId/text()", equalTo(i.toString())))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static void stubRequestToTop(String responseResource, Integer i) throws IOException {
        stubFor(post(urlEqualTo("/seashells/tops"))
                .withRequestBody(matchingXPath("/Envelope/Body/TopRequest/TopId/text()", equalTo(i.toString())))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static void stubRequestToLower(String responseResource, Integer i) throws IOException {
        stubFor(post(urlEqualTo("/seashells/lowers"))
                .withRequestBody(matchingXPath("/Envelope/Body/LowersRequest/LowerId/text()", equalTo(i.toString())))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static void stubRequestToAccount(String responseResource, Integer i) throws IOException {
        stubFor(post(urlEqualTo("/seashells/accounts"))
                .withRequestBody(matchingXPath("/Envelope/Body/AccountRequest/accountId/text()"))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static InputStreamReader getStringFromResource(String resourceName) {
        return new InputStreamReader(SeaShellWiremockSoapLauncher.class.getResourceAsStream(resourceName), Charset.defaultCharset());
    }
}
