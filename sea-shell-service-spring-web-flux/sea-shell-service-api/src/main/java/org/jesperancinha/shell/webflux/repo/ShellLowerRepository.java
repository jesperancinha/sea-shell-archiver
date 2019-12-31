package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.lowers.Lower;
import reactor.core.publisher.Mono;

public interface ShellLowerRepository {
    Mono<Lower> findLowerById(Long id);

    Lower findLowerByIdBlock(Long lowerId);
}
