package org.jesperancinha.ssa.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SeaShellApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeaShellApplication.class, args);

//        SeaShellWebClient shellWebClient = new SeaShellWebClient();
//        shellWebClient.consume();
    }

}