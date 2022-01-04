package org.jesperancinha.shell.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = WebMvcAutoConfiguration.class)
public class SeaShellApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaShellApplication.class, args);
    }

}