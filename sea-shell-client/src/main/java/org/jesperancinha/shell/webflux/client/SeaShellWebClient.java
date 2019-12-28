package org.jesperancinha.shell.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.model.SeaShell;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class SeaShellWebClient {

    public static void main(String[] args) {
        SeaShellWebClient seaShellWebClient = new SeaShellWebClient("http://localhost:8080");
        seaShellWebClient.consume();
    }

    private String uri;

    public SeaShellWebClient(final String uri) {
        this.uri = uri;
    }

    private final WebClient client = WebClient.builder().baseUrl(uri).build();

    public void consume() {

        client.get()
                .uri(uri + "/seashells/{id}/", "16")
                .retrieve()
                .bodyToMono(SeaShell.class)
                .subscribe(x -> log.info(x.toString()));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        client.get()
//                .uri(uri + "/seashells")
//                .retrieve()
//                .bodyToFlux(SeaShell.class)
//                .subscribe(x -> log.info(x.toString()));
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public Mono<SeaShell> getSeaShellById(Long id) {
        return client.get()
                .uri("/seashells/{id}", id)
                .retrieve()
                .bodyToMono(SeaShell.class);
    }

    public Flux<SeaShell> getAllSeaShells() {
        return client.get()
                .uri("/seashells")
                .retrieve()
                .bodyToFlux(SeaShell.class);
    }
}
