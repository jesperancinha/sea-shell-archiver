package org.jesperancinha.shell.client.costumes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

/**
 * Created by jofisaes on 02/08/2021
 */
public class CostumesClient extends WebServiceGatewaySupport {

    @Value("${sea.shell.cli.soap.costumes}")
    private String seaShellsWSDLCostumesClientLocation;

    public Costume getCostume(Long costumeId) {

        CostumesRequest request = new CostumesRequest();
        request.setCostumeId(costumeId);

        return ((JAXBElement<Costume>) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLCostumesClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/costumes")))).getValue();
    }

}