package org.jesperancinha.shell.client.persons;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2019-12-20T11:26:09.141+01:00
 * Generated source version: 3.3.4
 */
@Slf4j
public final class SeaShellsWSDLPersonsClient extends SeaShellsWSDLPersonsAbstract {

    private static final QName SERVICE_NAME = new QName("http://org.jesperancinha.shells/SeaShellsWSDLPersons/", "SeaShellsWSDLPersons");

    public SeaShellsWSDLPersonsClient(String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLPersonsClient(URL url) {
        super(url);
    }

    public Person getItem(Long itemId) {
        SeaShellsWSDLPersonsService ss = new SeaShellsWSDLPersonsService(url, SERVICE_NAME);
        SeaShellsWSDLPersons port = ss.getSeaShellsWSDLPersonsSOAP();
        log.trace("Invoking persons...");
        Person person = port.persons(itemId);
        log.trace("persons.result=" + person);
        return person;
    }

    public URL getLocalWsdlLocation() throws MalformedURLException, URISyntaxException {
        return SeaShellsWSDLPersonsClient.class.getResource("/SeaShellsWSDLPersons.wsdl").toURI().toURL();
    }
}
