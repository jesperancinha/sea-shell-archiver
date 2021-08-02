package org.jesperancinha.shell.client.costumes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by jofisaes on 02/08/2021
 */
@Configuration
public class CostumesConfiguration {


    @Bean
    public CostumesClient costumesClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.costumes");
        CostumesClient client = new CostumesClient();
        client.setDefaultUri("http://localhost:8090/seashells/costumes");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}