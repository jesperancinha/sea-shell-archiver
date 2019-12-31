package org.jesperancinha.shell.webflux.repo;

import org.jesperancinha.shell.client.persons.Person;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;

import java.util.List;

public interface ShellPersonRepository {
    Mono<Person> findPersonById(Long id);

    ParallelFlux<Person> findPersons(List<Long> costumeIds);

    List<Person> findPersonsBlock(List<Long> costumeIds);
}
