package org.jesperancinha.shell.client.shells;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;
import java.net.URL;
import java.util.List;

/**
 * Created by jofisaes on 02/08/2021
 */
public class ShellsClient extends WebServiceGatewaySupport {

    @Value("${sea.shell.cli.soap.shells}")
    private String seaShellsWSDLShellsClientLocation;

    public Shell getShell(Long id) {

        ShellRequest request = new ShellRequest();
        request.setShellId(id);

        return ((JAXBElement<Shell>)(getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLShellsClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/shells")))).getValue();
    }

    public List<Shell> getAllShells() {
        return (List<Shell>) getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLShellsClientLocation, new AllShellRequest(),
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/allshells"));
    }
    public List<Long> getAllShellIds() {
        return (List<Long>) getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLShellsClientLocation, new AllShellRequest(),
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/allshellIds"));
    }

}