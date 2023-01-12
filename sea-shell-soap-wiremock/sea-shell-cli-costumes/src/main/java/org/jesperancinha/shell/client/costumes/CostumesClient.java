package org.jesperancinha.shell.client.costumes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

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
        try {
            final CostumesRequest request = new CostumesRequest();
            request.setCostumeId(costumeId);
            return (Costume) (getWebServiceTemplate()
                    .marshalSendAndReceive(seaShellsWSDLCostumesClientLocation, request,
                            new SoapActionCallback(
                                    "http://org.jesperancinha.shells/SeaShellsWSDLShells/costumes")));
        } catch (Exception exception){
            logger.error("Costume %s retrieval failed!".formatted(costumeId));
            logger.error("ERROR",exception);
           return new Costume();
        }
    }

}