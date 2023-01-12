package org.jesperancinha.shell.client.tops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by jofisaes on 02/08/2021
 */

@Component
public class TopsClient extends WebServiceGatewaySupport {
    @Value("${sea.shell.cli.soap.tops}")
    private String seaShellsWSDLTopsClientLocation;

    public TopsClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.tops");
        this.setDefaultUri(seaShellsWSDLTopsClientLocation);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public Top getTop(Long id) {
        final TopRequest request = new TopRequest();
        request.setTopId(id);
        try {
            return (Top) (getWebServiceTemplate()
                    .marshalSendAndReceive(seaShellsWSDLTopsClientLocation, request,
                            new SoapActionCallback(
                                    "http://org.jesperancinha.shells/SeaShellsWSDLShells/tops")));
        } catch (Exception exception) {
            logger.error("Costume %s retrieval failed!".formatted(id));
            logger.error("ERROR", exception);
            return new Top();
        }
    }
}