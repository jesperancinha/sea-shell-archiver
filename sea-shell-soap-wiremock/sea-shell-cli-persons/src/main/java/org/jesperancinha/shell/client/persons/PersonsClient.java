package org.jesperancinha.shell.client.persons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by jofisaes on 02/08/2021
 */
@Component
public class PersonsClient extends WebServiceGatewaySupport {

    @Value("${sea.shell.cli.soap.persons}")
    private String seaShellsWSDLPersonsClientLocation;

    public PersonsClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.persons");
        this.setDefaultUri(seaShellsWSDLPersonsClientLocation);
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public Person getPerson(Long personId) {

        PersonsRequest request = new PersonsRequest();
        request.setPersonId(personId);

        return (Person) (getWebServiceTemplate()
                .marshalSendAndReceive(seaShellsWSDLPersonsClientLocation, request,
                        new SoapActionCallback(
                                "http://org.jesperancinha.shells/SeaShellsWSDLPersons/persons")));
    }

}