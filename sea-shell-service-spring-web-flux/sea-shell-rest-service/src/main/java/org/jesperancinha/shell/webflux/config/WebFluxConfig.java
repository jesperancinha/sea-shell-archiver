package org.jesperancinha.shell.webflux.config;

import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAccountsAbstract;
import org.jesperancinha.shell.client.accounts.SeaShellsWSDLAccountsClient;
import org.jesperancinha.shell.client.costumes.CostumesConfiguration;
import org.jesperancinha.shell.client.lowers.SeaShellsWSDLLowersAbstract;
import org.jesperancinha.shell.client.lowers.SeaShellsWSDLLowersClient;
import org.jesperancinha.shell.client.persons.PersonsConfiguration;
import org.jesperancinha.shell.client.shells.ShellsConfiguration;
import org.jesperancinha.shell.client.tops.SeaShellsWSDLTopAbstract;
import org.jesperancinha.shell.client.tops.SeaShellsWSDLTopsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.net.URL;

@Configuration
@EnableWebFlux
@Import({CostumesConfiguration.class, ShellsConfiguration.class, PersonsConfiguration.class})
public class WebFluxConfig {

    @Value("${sea.shell.cli.soap.accounts}")
    private URL seaShellsWSDLAccountsClientLocation;

    @Value("${sea.shell.cli.soap.tops}")
    private URL seaShellsWSDLTopsClientLocation;

    @Value("${sea.shell.cli.soap.lowers}")
    private URL seaShellsWSDLLowersClientLocation;

    @Bean
    public SeaShellsWSDLAccountsAbstract seaShellsWSDLAccountsClient() {
        return new SeaShellsWSDLAccountsClient(seaShellsWSDLAccountsClientLocation);
    }

    @Bean
    public SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient() {
        return new SeaShellsWSDLLowersClient(seaShellsWSDLLowersClientLocation);
    }

    @Bean
    public SeaShellsWSDLTopAbstract seaShellsWSDLTopClient() {
        return new SeaShellsWSDLTopsClient(seaShellsWSDLTopsClientLocation);
    }
}
