package org.jesperancinha.shell.client.shells;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;
import java.util.List;

/**
 * Created by jofisaes on 02/08/2021
 */
@Component
public class ShellsClient extends WebServiceGatewaySupport {

    @Value("${sea.shell.cli.soap.shells}")
    private String seaShellsWSDLShellsClientLocation;

    public ShellsClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.shells");
        this.setDefaultUri(seaShellsWSDLShellsClientLocation);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public Shell getShell(Long id) {

        ShellRequest request = new ShellRequest();
        request.setShellId(id);

        return ((JAXBElement<Shell>) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLShellsClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/shells")))).getValue();
    }

    public List<Shell> getAllShells() {
        return ((JAXBElement<List<Shell>>) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLShellsClientLocation, new AllShellRequest(),
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/allshells")))).getValue();
    }

    public List<Long> getAllShellIds() {
        return ((AllShellIdsResponse) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLShellsClientLocation, new AllShellRequest(),
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/allshellIds")))).shellId;
    }

}