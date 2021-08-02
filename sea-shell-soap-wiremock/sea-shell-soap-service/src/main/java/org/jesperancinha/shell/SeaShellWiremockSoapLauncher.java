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
        stubRequestToResponse("/seashells/shells", "/mock/requests/shells/allShells.xml", "/mock/responses/shells/allShells.xml");
        stubRequestToResponse("/seashells/costumes", "/mock/requests/costumes/costume1.xml", "/mock/responses/costumes/costume1.xml");
        stubRequestToResponse("/seashells/costumes", "/mock/requests/costumes/costume2.xml", "/mock/responses/costumes/costume2.xml");
        stubRequestToResponse("/seashells/costumes", "/mock/requests/costumes/costume3.xml", "/mock/responses/costumes/costume3.xml");
        stubRequestToResponse("/seashells/lowers", "/mock/requests/lowers/lower1.xml", "/mock/responses/lowers/lower1.xml");
        stubRequestToResponse("/seashells/lowers", "/mock/requests/lowers/lower2.xml", "/mock/responses/lowers/lower2.xml");
        stubRequestToResponse("/seashells/tops", "/mock/requests/tops/top1.xml", "/mock/responses/tops/top1.xml");
        stubRequestToResponse("/seashells/tops", "/mock/requests/tops/top2.xml", "/mock/responses/tops/top2.xml");
        stubRequestToResponse("/seashells/accounts", "/mock/requests/accounts/account1.xml", "/mock/responses/accounts/account1.xml");
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
            stubRequestToShell("/mock/requests/shells/shell" + i + ".xml", "/mock/responses/shells/shell" + i + ".xml", i);

        }
    }

    private static void stubPersons() throws IOException {
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person1.xml", "/mock/responses/persons/person1.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person2.xml", "/mock/responses/persons/person2.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person3.xml", "/mock/responses/persons/person3.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person4.xml", "/mock/responses/persons/person4.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person5.xml", "/mock/responses/persons/person5.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person6.xml", "/mock/responses/persons/person6.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person7.xml", "/mock/responses/persons/person7.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person8.xml", "/mock/responses/persons/person8.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person9.xml", "/mock/responses/persons/person9.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person10.xml", "/mock/responses/persons/person10.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person11.xml", "/mock/responses/persons/person11.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person12.xml", "/mock/responses/persons/person12.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person13.xml", "/mock/responses/persons/person13.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person14.xml", "/mock/responses/persons/person14.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person15.xml", "/mock/responses/persons/person15.xml");
        stubRequestToResponse("/seashells/persons", "/mock/requests/persons/person16.xml", "/mock/responses/persons/person16.xml");
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

    private static void stubRequestToShell(String requestResource, String responseResource, Integer i) throws IOException {
        stubFor(post(urlEqualTo("/seashells/shells"))
                .withRequestBody(matchingXPath("/Envelope/Body/shellRequest/shellId/text()", equalTo(i.toString())))
                .willReturn(aResponse().withBody(CharStreams.toString(getStringFromResource(responseResource)))
                        .withHeader(ContentTypeHeader.KEY, ContentType.TEXT_XML.getMimeType())));
    }

    private static InputStreamReader getStringFromResource(String resourceName) {
        return new InputStreamReader(SeaShellWiremockSoapLauncher.class.getResourceAsStream(resourceName), Charset.defaultCharset());
    }
}
