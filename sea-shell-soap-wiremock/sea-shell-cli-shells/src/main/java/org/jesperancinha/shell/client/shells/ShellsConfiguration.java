package org.jesperancinha.shell.client.shells;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.net.URL;

/**
 * Created by jofisaes on 02/08/2021
 */
@Configuration
public class ShellsConfiguration {

    @Value("${sea.shell.cli.soap.shells}")
    private String seaShellsWSDLShellsClientLocation;

    @Bean
    public ShellsClient shellsClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.shells");
        ShellsClient client = new ShellsClient();
        client.setDefaultUri(seaShellsWSDLShellsClientLocation);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}