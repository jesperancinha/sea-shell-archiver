package org.jesperancinha.shell.webflux.config;

import org.jesperancinha.shell.client.lowers.SeaShellsWSDLLowersAbstract;
import org.jesperancinha.shell.client.lowers.SeaShellsWSDLLowersClient;
import org.jesperancinha.shell.client.tops.SeaShellsWSDLTopAbstract;
import org.jesperancinha.shell.client.tops.SeaShellsWSDLTopsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class WebFluxConfig {

    @Value("${sea.shell.cli.soap.tops}")
    private URL seaShellsWSDLTopsClientLocation;

    @Value("${sea.shell.cli.soap.lowers}")
    private URL seaShellsWSDLLowersClientLocation;

    @Bean
    public SeaShellsWSDLLowersAbstract seaShellsWSDLLowersClient() {
        return new SeaShellsWSDLLowersClient(seaShellsWSDLLowersClientLocation);
    }

    @Bean
    public SeaShellsWSDLTopAbstract seaShellsWSDLTopClient() {
        return new SeaShellsWSDLTopsClient(seaShellsWSDLTopsClientLocation);
    }
}
