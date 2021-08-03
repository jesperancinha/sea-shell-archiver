package org.jesperancinha.shell.client.lowers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.net.URL;

/**
 * Created by jofisaes on 02/08/2021
 */
@Configuration
public class LowersConfiguration {
    @Value("${sea.shell.cli.soap.lowers}")
    private String seaShellsWSDLLowersClientLocation;

    @Bean
    public LowersClient shellsClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.lowers");
        LowersClient client = new LowersClient();
        client.setDefaultUri(seaShellsWSDLLowersClientLocation);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}