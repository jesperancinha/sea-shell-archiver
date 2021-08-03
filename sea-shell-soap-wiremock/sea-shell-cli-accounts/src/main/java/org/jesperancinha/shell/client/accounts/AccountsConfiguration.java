package org.jesperancinha.shell.client.accounts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by jofisaes on 02/08/2021
 */
@Configuration
public class AccountsConfiguration {
    @Value("${sea.shell.cli.soap.accounts}")
    private String seaShellsWSDLAccountsClientLocation;


    @Bean
    public AccountsClient costumesClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.jesperancinha.shell.client.accounts");
        AccountsClient client = new AccountsClient();
        client.setDefaultUri(seaShellsWSDLAccountsClientLocation);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}