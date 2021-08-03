package org.jesperancinha.shell.webflux;

import org.jesperancinha.shell.client.accounts.AccountsConfiguration;
import org.jesperancinha.shell.client.costumes.CostumesConfiguration;
import org.jesperancinha.shell.client.persons.PersonsConfiguration;
import org.jesperancinha.shell.client.shells.ShellsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@Import({
        CostumesConfiguration.class,
        ShellsConfiguration.class,
        PersonsConfiguration.class,
        AccountsConfiguration.class
})
@SpringBootApplication(exclude = WebMvcAutoConfiguration.class)
public class SeaShellApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaShellApplication.class, args);
    }

}