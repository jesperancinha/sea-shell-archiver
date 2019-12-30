package org.jesperancinha.shell.webflux.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;

import javax.xml.ws.WebServiceException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static reactor.core.publisher.Mono.delay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SeaShellServiceTest {

    @Autowired
    private SeaShellService seaShellService;

    @BeforeAll
    public static void setUpAll() {
        BlockHound.install();
    }

    @Test
    public void findAllCompleteSeaShells_onCall_thenNonBlocking() {
        delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShells())
                .block();
    }

    @Test
    public void findAllCompleteSeaShellsBlock_onCall_thenBlocking() {
        assertThrows(WebServiceException.class, () -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShellsNaifBlock())
                .block());
    }

    @Test
    public void findAllCompleteSeaShellsReactiveBlock_onCall_thenNonBlocking() {
        delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShellsReactiveBlock())
                .block();
    }

    @Test
    public void findAllCompleteSeaShellsReactiveWithDelay_onCall_thenNonBlocking() {
        delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellService.getAllSeaShellsReactiveWithDelay())
                .block();
    }

}