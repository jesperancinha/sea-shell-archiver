package org.jesperancinha.shell.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.client.data.SeaShellDto;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
public class SeaShellWebReactiveClient {

    private final WebClient client;

    public SeaShellWebReactiveClient(final String uri) {
        this.client = WebClient.builder().baseUrl(uri).build();
    }

    public static void main(String[] args) throws InterruptedException {
        SeaShellWebReactiveClient seaShellWebReactiveClient = new SeaShellWebReactiveClient("http://localhost:8080");
        seaShellWebReactiveClient.consumeReactively();
    }

    private void consumeReactively() throws InterruptedException {
        getAllSeaShellsReactively().subscribe(x -> log.info("BLOCK->" + x.toString()));
        Thread.sleep(1000);
    }

    public Flux<SeaShellDto> getAllSeaShellsReactively() {
        return client.get()
                .uri("/seashells/reactive")
                .retrieve()
                .bodyToFlux(SeaShellDto.class);
    }

}
