package org.jesperancinha.shell.webflux.immutable.service;

import io.mockk.MockKKt;
import io.mockk.impl.annotations.InjectMockKs;
import io.mockk.impl.annotations.MockK;
import io.mockk.junit5.MockKExtension;
import org.jesperancinha.shell.webflux.immutable.data.SeaShellDto;
import org.jesperancinha.shell.webflux.immutable.repository.ShellAccountImmutableRepository;
import org.jesperancinha.shell.webflux.immutable.repository.ShellCostumeImmutableRepository;
import org.jesperancinha.shell.webflux.immutable.repository.ShellImmutableRepository;
import org.jesperancinha.shell.webflux.immutable.repository.ShellLowerImmutableRepository;
import org.jesperancinha.shell.webflux.immutable.repository.ShellPersonImmutableRepository;
import org.jesperancinha.shell.webflux.immutable.repository.ShellTopImmutableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.mockk.MockKKt.every;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockKExtension.class)
class SeaShellsReactiveImmutableServiceUnitTest {

    @MockK(relaxed = true)
    private ShellTopImmutableRepository shellTopRepository;
    @MockK(relaxed = true)
    private ShellLowerImmutableRepository shellLowerRepository;
    @MockK(relaxed = true)
    private ShellAccountImmutableRepository shellAccountImmutableRepository;
    @MockK(relaxed = true)
    private ShellCostumeImmutableRepository shellCostumeImmutableRepository;
    @MockK(relaxed = true)
    private ShellPersonImmutableRepository shellPersonImmutableRepository;
    @MockK(relaxed = true)
    private ShellImmutableRepository shellImmutableRepository;
    @InjectMockKs
    private SeaShellsReactiveImmutableService seaShellsReactiveImmutableService;
    @Test
    void testGetAllShells() {
        every(mockKMatcherScope -> shellImmutableRepository.findAllShellIds()).returns(Flux.from(Mono.empty()));

        final SeaShellDto seaShellDto = seaShellsReactiveImmutableService.getAllShells().blockFirst();

        assertThat(seaShellDto).isNull();
    }

    @Test
    void testGetSeaShellById() {
    }

    @Test
    void testGetPersonById() {
    }

    @Test
    void testGetCostumeById() {
    }

    @Test
    void testGetAccountById() {
    }

    @Test
    void testGetTopById() {
    }

    @Test
    void testGetLowerById() {
    }
}