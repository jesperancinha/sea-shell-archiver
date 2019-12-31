package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.tops.Top;
import reactor.core.publisher.Mono;

public interface ShellTopRepository {
    Mono<Top> findTopById(Long id);

    Top findTopByIdBlock(Long id);
}
