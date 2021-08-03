package org.jesperancinha.shell.client.tops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.net.URL;

/**
 * Created by jofisaes on 02/08/2021
 */
@Configuration
public class TopsConfiguration {
    @Value("${sea.shell.cli.soap.tops}")
    private String seaShellsWSDLTopsClientLocation;

    @Bean
    public TopsClient shellsClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.tops");
        TopsClient client = new TopsClient();
        client.setDefaultUri(seaShellsWSDLTopsClientLocation);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}