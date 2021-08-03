package org.jesperancinha.shell.client.tops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;
import java.net.URL;
import java.util.List;

/**
 * Created by jofisaes on 02/08/2021
 */
public class TopsClient extends WebServiceGatewaySupport {
    @Value("${sea.shell.cli.soap.tops}")
    private String seaShellsWSDLTopsClientLocation;

    public Top getTop(Long id) {

        TopRequest request = new TopRequest();
        request.setTopId(id);

        return ((JAXBElement<Top>)(getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLTopsClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/tops")))).getValue();
    }
}