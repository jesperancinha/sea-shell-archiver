package org.jesperancinha.shell.webflux.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Created by jofisaes on 03/08/2021
 */
@Configuration
@EnableWebFlux
@ComponentScan("org.jesperancinha.shell.client")
public class SeaShellServiceConfiguration {
}
