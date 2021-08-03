package org.jesperancinha.shell.client.lowers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

/**
 * Created by jofisaes on 02/08/2021
 */
public class LowersClient extends WebServiceGatewaySupport {
    @Value("${sea.shell.cli.soap.lowers}")
    private String seaShellsWSDLLowersClientLocation;

    public Lower getlLower(Long id) {

        LowersRequest request = new LowersRequest();
        request.setLowerId(id);

        return ((JAXBElement<Lower>)(getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLLowersClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/tops")))).getValue();
    }
}