package org.jesperancinha.shell.webflux.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;

import java.time.Duration;

import static reactor.core.publisher.Mono.delay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SeaShellReactiveServiceImplTest {

    @Autowired
    private SeaShellReactiveService seaShellReactiveService;

    @BeforeAll
    public static void setUpAll() {
        BlockHound.install();
    }

    @Test
    public void findAllCompleteSeaShells_onCall_thenNonBlocking() {
        delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellReactiveService.getAllSeaShells())
                .block();
    }
}