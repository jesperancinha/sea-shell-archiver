package org.jesperancinha.ssa.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.ssa.webflux.model.SeaShell;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class SeaShellWebClient {
    final WebClient client = WebClient.builder().baseUrl("http://localhost:8080").build();

    public void consume() {

        Mono<SeaShell> seaShellMono = client.get()
                .uri("/seashells/{id}", "1")
                .retrieve()
                .bodyToMono(SeaShell.class);

        seaShellMono.subscribe(x -> {
            log.info(x.toString());
        });

        Flux<SeaShell> seaShellFlux = client.get()
                .uri("/seashells")
                .retrieve()
                .bodyToFlux(SeaShell.class);

        seaShellFlux.subscribe(System.out::println);
    }
}
