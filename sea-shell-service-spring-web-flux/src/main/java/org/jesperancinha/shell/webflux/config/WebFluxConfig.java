package org.jesperancinha.shell.webflux.config;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAccountsAbstract;
import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAccountsClient;
import org.jesperancinha.shell.client.costumes.SeaShellsWSDLCostumesAbstract;
import org.jesperancinha.shell.client.costumes.SeaShellsWSDLCostumesClient;
import org.jesperancinha.shell.client.persons.SeaShellsWSDLPersonsAbstract;
import org.jesperancinha.shell.client.persons.SeaShellsWSDLPersonsClient;
import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsAbstract;
import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.net.URL;

@Configuration
@EnableWebFlux
public class WebFluxConfig {

    @Value("${sea.shell.cli.soap.shells}")
    private URL seaShellsWSDLShellsClientLocation;

    @Value("${sea.shell.cli.soap.accounts}")
    private URL seaShellsWSDLAccountsClientLocation;

    @Value("${sea.shell.cli.soap.costumes}")
    private URL seaShellsWSDLCostumesClientLocation;

    @Value("${sea.shell.cli.soap.persons}")
    private URL seaShellsWSDLPersonsClientLocation;

    @Bean
    public SeaShellsWSDLShellsAbstract seaShellsWSDLShellsClient() {
        return new SeaShellsWSDLShellsClient(seaShellsWSDLShellsClientLocation);
    }

    @Bean
    public SeaShellsWSDLAccountsAbstract seaShellsWSDLAccountsClient() {
        return new SeaShellsWSDLAccountsClient(seaShellsWSDLAccountsClientLocation);
    }

    @Bean
    public SeaShellsWSDLCostumesAbstract seaShellsWSDLCostumesClient() {
        return new SeaShellsWSDLCostumesClient(seaShellsWSDLCostumesClientLocation);
    }

    @Bean
    public SeaShellsWSDLPersonsAbstract seaShellsWSDLPersonsClient() {
        return new SeaShellsWSDLPersonsClient(seaShellsWSDLPersonsClientLocation);
    }
}
