package org.jesperancinha.shell.webflux.immutable.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
@ComponentScan("org.jesperancinha.shell.client")
class SeaShellServiceConfiguration 