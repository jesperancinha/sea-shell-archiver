package org.jesperancinha.ssa.webflux;

import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class SeaShellWebClient
{
    WebClient client = WebClient.create("http://localhost:8080");

    public void consume() {

        Mono<SeaShell> seaShellMono = client.get()
                .uri("/seashells/{id}", "1")
                .retrieve()
                .bodyToMono(SeaShell.class);

        seaShellMono.subscribe(System.out::println);

        Flux<SeaShell> seaShellFlux = client.get()
                .uri("/seashells")
                .retrieve()
                .bodyToFlux(SeaShell.class);

        seaShellFlux.subscribe(System.out::println);
    }
}
