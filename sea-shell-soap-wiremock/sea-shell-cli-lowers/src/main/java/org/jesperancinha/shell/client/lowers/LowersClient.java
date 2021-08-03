package org.jesperancinha.shell.client.lowers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by jofisaes on 02/08/2021
 */
@Component
public class LowersClient extends WebServiceGatewaySupport {
    @Value("${sea.shell.cli.soap.lowers}")
    private String seaShellsWSDLLowersClientLocation;

    public LowersClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.lowers");
        this.setDefaultUri(seaShellsWSDLLowersClientLocation);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public Lower getLower(Long id) {

        final LowersRequest request = new LowersRequest();
        request.setLowerId(id);

        return (Lower) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLLowersClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/lowers")));
    }
}