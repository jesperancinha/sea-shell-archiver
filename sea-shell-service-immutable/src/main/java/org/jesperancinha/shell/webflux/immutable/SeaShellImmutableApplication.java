package org.jesperancinha.shell.webflux.immutable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = WebMvcAutoConfiguration.class)
public class SeaShellImmutableApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaShellImmutableApplication.class, args);
    }

}