package org.jesperancinha.shell.webflux.config;

import org.jesperancinha.shell.client.shells.SeaShellsWSDLShellsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.net.URI;
import java.net.URL;

@Configuration
@EnableWebFlux
public class WebFluxConfig {

    @Value("${sea.shell.cli.soap.shells}")
    private URL seaShellsWSDLShellsClientLocation;

    @Bean
    public SeaShellsWSDLShellsClient seaShellsWSDLShellsClient(){
        return new SeaShellsWSDLShellsClient(seaShellsWSDLShellsClientLocation);
    }
}
