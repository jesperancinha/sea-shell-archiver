package org.jesperancinha.shell.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.model.SeaShell;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Slf4j
public class SeaShellWebClient {

    private final WebClient client;

    public SeaShellWebClient(final String uri) {
        this.client = WebClient.builder().baseUrl(uri).build();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SeaShellWebClient seaShellWebClient = new SeaShellWebClient("http://localhost:8080");
        seaShellWebClient.consume();
    }

    public void consume() throws InterruptedException {
        getSeaShellById(1L).subscribe(x -> log.info("REACTIVE ONE->" + x.toString()));
        getAllSeaShells().subscribe(x -> log.info("REACTIVE ALL->" + x.toString()));
        getAllSeaShellsBlock().subscribe(x -> log.info("BLOCK->" + x.toString()));
        final SeaShellDto seaShellDtoById = getSeaShellDtoById(1L);
        final SeaShellDto seaShellDtoNaifById = getSeaShellDtoNaifById(1L);
        log.info("REACTIVE DTO->" + seaShellDtoById.toString());
        log.info("NAIF DTO->" + seaShellDtoNaifById.toString());
        Thread.sleep(1000);
    }

    public SeaShellDto getSeaShellDtoById(Long id) {
        return client.get()
                .uri("/seashells/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellDto.class)
                .block();
    }

    public SeaShellDto getSeaShellDtoNaifById(Long id) {
        return client.get()
                .uri("/seashells/block/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellDto.class)
                .block();
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

    public Flux<SeaShell> getAllSeaShellsBlock() {
        return client.get()
                .uri("/seashells/block")
                .retrieve()
                .bodyToFlux(SeaShell.class);
    }
}
