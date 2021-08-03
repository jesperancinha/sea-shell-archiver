package org.jesperancinha.shell.client.costumes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

/**
 * Created by jofisaes on 02/08/2021
 */
@Component
public class CostumesClient extends WebServiceGatewaySupport {

    @Value("${sea.shell.cli.soap.costumes}")
    private String seaShellsWSDLCostumesClientLocation;

    public CostumesClient(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.costumes");
        this.setDefaultUri(seaShellsWSDLCostumesClientLocation);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public Costume getCostume(Long costumeId) {
        final CostumesRequest request = new CostumesRequest();
        request.setCostumeId(costumeId);

        return (Costume) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLCostumesClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/costumes")));
    }

}