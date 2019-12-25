
package org.jesperancinha.shell.client.costumes;

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
public final class SeaShellsWSDLCostumesClient extends SeaShellsWSDLCostumesAbstract {

    private static final QName SERVICE_NAME = new QName("http://org.jesperancinha.shells/SeaShellsWSDLCostumes/", "SeaShellsWSDLCostumes");

    private final URL url;

    public SeaShellsWSDLCostumesClient(String[] args) throws MalformedURLException, URISyntaxException {

        this.url = getUrlFromCommandLine(args);
    }

    public SeaShellsWSDLCostumesClient(URL url) {
        this.url = url;
    }

    @Override
    public Costume getItem(int costumeId) {
        SeaShellsWSDLCostumesService ss = new SeaShellsWSDLCostumesService(url, SERVICE_NAME);
        SeaShellsWSDLCostumes port = ss.getSeaShellsWSDLCostumesSOAP();
        log.trace("Invoking costumes...");
        int _costumes_costumeId = 1;
        Costume costumesReturn = port.costumes(_costumes_costumeId);
        log.trace("costumes.result=" + costumesReturn);
        return costumesReturn;
    }

    @Override
    public URL getLocalWsdlLocation() throws MalformedURLException, URISyntaxException {
        return SeaShellsWSDLCostumesClient.class.getResource("/SeaShellsWSDLCostumes.wsdl").toURI().toURL();
    }


}
