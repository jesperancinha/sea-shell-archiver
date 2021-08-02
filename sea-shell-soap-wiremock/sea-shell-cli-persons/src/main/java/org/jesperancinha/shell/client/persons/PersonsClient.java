package org.jesperancinha.shell.client.persons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

/**
 * Created by jofisaes on 02/08/2021
 */
public class PersonsClient extends WebServiceGatewaySupport {

    @Value("${sea.shell.cli.soap.persons}")
    private String seaShellsWSDLPersonsClientLocation;

    public Person getPerson(Long personId) {

        PersonsRequest request = new PersonsRequest();
        request.setPersonId(personId);

        return  ((JAXBElement<Person>)(getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLPersonsClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLShells/persons")))).getValue();
    }

}