package org.jesperancinha.shell.webflux.service;

import org.jesperancinha.shell.webflux.repo.ShellRepository;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static reactor.core.publisher.Mono.delay;

public class SeaShellServiceTest {
    private SeaShellService seaShellService = new SeaShellService(new ShellRepository());

    @Test
    public void findAllCompleteSeaShells() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.findAllCompleteSeaShells())
                .block());
    }
}