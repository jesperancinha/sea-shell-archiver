package org.jesperancinha.shell.client.persons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.net.URL;

/**
 * Created by jofisaes on 02/08/2021
 */
@Configuration
public class PersonsConfiguration {

    @Value("${sea.shell.cli.soap.persons}")
    private String seaShellsWSDLPersonsClientLocation;

    @Bean
    public PersonsClient costumesClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.persons");
        PersonsClient client = new PersonsClient();
        client.setDefaultUri(seaShellsWSDLPersonsClientLocation);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}