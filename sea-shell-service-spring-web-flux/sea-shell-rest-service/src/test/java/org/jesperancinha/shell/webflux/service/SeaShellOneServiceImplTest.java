package org.jesperancinha.shell.webflux.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static reactor.core.publisher.Mono.delay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SeaShellOneServiceImplTest {

    @BeforeAll
    public static void setUpAll() {
        BlockHound.install();
    }

    @Autowired
    private SeaShellOneService seaShellOneService;

    @Test
    public void findAllIds_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellOneService.getAllIds())
                .block());
    }

    @Test
    public void findSeaShellById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellOneService.getSeaShellById(1L))
                .block());
    }

    @Test
    public void findPersonById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellOneService.getPersonById(1L))
                .block());
    }

    @Test
    public void findCostumeById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellOneService.getCostumeById(1L))
                .block());
    }

    @Test
    public void findAccountById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellOneService.getAccountById("ACCOUNTID"))
                .block());
    }

    @Test
    public void findTopById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellOneService.getTopById(1L))
                .block());
    }

    @Test
    public void findLowerById_onCall_thenNonBlocking() {
        assertAll(() -> delay(Duration.ofSeconds(1))
                .doOnNext(it -> seaShellOneService.getLowerById(1L))
                .block());
    }
}