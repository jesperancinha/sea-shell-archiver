package org.jesperancinha.shell.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.client.future.SeaShellWebClientOneHelper;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

@Slf4j
public class SeaShellWebReactiveOneClient {

    private final WebClient client;

    public SeaShellWebReactiveOneClient(final String uri) {
        this.client = WebClient.builder().baseUrl(uri).build();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        SeaShellWebReactiveOneClient seaShellWebReactiveOneClient = new SeaShellWebReactiveOneClient("http://localhost:8080");
        seaShellWebReactiveOneClient.consumeReactively();
    }

    private void consumeReactively() throws ExecutionException, InterruptedException {
        final ExecutorService executorService = newCachedThreadPool();
        final SeaShellWebClientOneHelper seaShellWebClientOneHelper = new SeaShellWebClientOneHelper(this.client);
        log.info("REACTIVE WAY ONE->" + seaShellWebClientOneHelper.getMultipleRequestClientSideOneShell(1L, executorService).toString());
        log.info("REACTIVE WAY ALL->" + seaShellWebClientOneHelper.getMultipleRequestClientSideOneShell(executorService).toString());
        executorService.shutdown();
    }
}
