package org.jesperancinha.shell.client.shells;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2019-12-20T11:26:09.141+01:00
 * Generated source version: 3.3.4
 */
@Slf4j
public final class SeaShellsWSDLShellsClient extends SeaShellsWSDLShellsAbstract {

    private static final QName SERVICE_NAME = new QName("http://org.jesperancinha.shells/SeaShellsWSDLShells/", "SeaShellsWSDLShells");

    public SeaShellsWSDLShellsClient(String[] args) throws MalformedURLException, URISyntaxException {
        super(args);
    }

    public SeaShellsWSDLShellsClient(URL url) {
        super(url);
    }

    public Shell getItem(Long itemId) {
        SeaShellsWSDLShellsService ss = new SeaShellsWSDLShellsService(url, SERVICE_NAME);
        SeaShellsWSDLShells port = ss.getSeaShellsWSDLShellsSOAP();
        log.trace("Invoking shells...");
        try {
            Shell shellsReturn = port.shells(itemId);
            log.trace("shells.result=" + shellsReturn);
            return shellsReturn;
        } catch (Exception e) {
            return null;
        }
    }

    public URL getLocalWsdlLocation() throws MalformedURLException, URISyntaxException {
        return SeaShellsWSDLShellsClient.class.getResource("/SeaShellsWSDLShells.wsdl").toURI().toURL();
    }

    public List<Long> getAllShellIds() {
        SeaShellsWSDLShellsService ss = new SeaShellsWSDLShellsService(url, SERVICE_NAME);
        SeaShellsWSDLShells port = ss.getSeaShellsWSDLShellsSOAP();
        log.trace("Invoking shells...");
        final AllShell shellsReturn = port.allShells();
        log.trace("shells.result=" + shellsReturn);
        return shellsReturn.getShellIds();
    }
}