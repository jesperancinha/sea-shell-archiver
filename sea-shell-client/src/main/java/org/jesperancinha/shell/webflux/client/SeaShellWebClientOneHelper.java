package org.jesperancinha.shell.webflux.client;

import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.shell.webflux.data.SeaShellAccountDto;
import org.jesperancinha.shell.webflux.data.SeaShellCostumeDto;
import org.jesperancinha.shell.webflux.data.SeaShellDto;
import org.jesperancinha.shell.webflux.data.SeaShellLowerDto;
import org.jesperancinha.shell.webflux.data.SeaShellPersonDto;
import org.jesperancinha.shell.webflux.data.SeaShellTopDto;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.concurrent.Executors.newCachedThreadPool;

@Slf4j
public class SeaShellWebClientOneHelper {

    private final WebClient client;

    public SeaShellWebClientOneHelper(WebClient client) {
        this.client = client;
    }

    public List<SeaShellDto> getMultipleRequestClientSideOneShell() throws ExecutionException, InterruptedException {
        final ExecutorService executorService = newCachedThreadPool();
        final List<SeaShellDto> seaShellDtoList = new ArrayList<>();
        final Future<?> shells = executorService.submit(() -> stream(getSeaShellOneAll()).parallel().forEach(seaShellId -> {
            try {
                seaShellDtoList.add(getMultipleRequestClientSideOneShell(seaShellId));
            } catch (ExecutionException | InterruptedException e) {
                log.error("Error!", e);
            }
        }));
        shells.get();
        executorService.shutdown();
        return seaShellDtoList;
    }

    public SeaShellDto getMultipleRequestClientSideOneShell(Long id) throws ExecutionException, InterruptedException {
        final ExecutorService executorService = newCachedThreadPool();
        final SeaShellDto seaShellOneDtoById = getSeaShellOneDtoById(id);
        final Future<?> costumes = executorService.submit(costumesRunnable(seaShellOneDtoById));
        final Future<?> persons = executorService.submit(personsRunnable(seaShellOneDtoById));
        costumes.get();
        persons.get();
        executorService.shutdown();
        return seaShellOneDtoById;
    }

    private Runnable personsRunnable(SeaShellDto seaShellOneDtoById) {
        return () -> seaShellOneDtoById.setPersons(seaShellOneDtoById.getPersonIds().parallelStream().map(id ->
        {
            try {
                SeaShellPersonDto seaShellOnePersonDtoById = getSeaShellOnePersonDtoById(id);
                final ExecutorService executorService = newCachedThreadPool();
                Future<?> costume = executorService.submit(() -> seaShellOnePersonDtoById.setCostumeDto(getCostumeDto(seaShellOnePersonDtoById.getCostumeId())));
                Future<?> account = executorService.submit(() -> seaShellOnePersonDtoById.setAccountDto(getSeaShellOneAccountDtoById(seaShellOnePersonDtoById.getAccountId())));
                account.get();
                costume.get();
                executorService.shutdown();
                return seaShellOnePersonDtoById;
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error!", e);
            }
            return null;
        }).collect(Collectors.toList()));
    }

    private Runnable costumesRunnable(SeaShellDto seaShellOneDtoById) {
        return () -> seaShellOneDtoById.setCostumes(
                seaShellOneDtoById.getCostumeIds()
                        .parallelStream()
                        .map(this::getCostumeDto)
                        .collect(Collectors.toList()));
    }

    public Long[] getSeaShellOneAll() {
        final List<Long> list = new ArrayList<>();
        return client.get()
                .uri("/seashells/one")
                .retrieve()
                .bodyToMono(Long[].class)
                .block();
    }

    private SeaShellCostumeDto getCostumeDto(Long id) {
        try {
            final SeaShellCostumeDto seaShellOneCostumeDtoById = getSeaShellOneCostumeDtoById(id);
            final ExecutorService executorService = newCachedThreadPool();
            Future<?> tops = executorService.submit(() -> seaShellOneCostumeDtoById.setTopDto(getSeaShellOneTopDtoById(seaShellOneCostumeDtoById.getTopId())));
            Future<?> lowers = executorService.submit(() -> seaShellOneCostumeDtoById.setLowerDto(getSeaShellOneLowerDtoById(seaShellOneCostumeDtoById.getLowerId())));
            tops.get();
            lowers.get();
            executorService.shutdown();
            return seaShellOneCostumeDtoById;
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error!", e);
        }
        return null;
    }

    public SeaShellDto getSeaShellOneDtoById(Long id) {
        return client.get()
                .uri("/seashells/one/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellDto.class)
                .block();
    }

    public SeaShellPersonDto getSeaShellOnePersonDtoById(Long id) {
        return client.get()
                .uri("/seashells/one/person/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellPersonDto.class)
                .block();
    }

    public SeaShellCostumeDto getSeaShellOneCostumeDtoById(Long id) {
        return client.get()
                .uri("/seashells/one/costume/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellCostumeDto.class)
                .block();
    }

    public SeaShellTopDto getSeaShellOneTopDtoById(Long id) {
        return client.get()
                .uri("/seashells/one/top/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellTopDto.class)
                .block();
    }

    public SeaShellLowerDto getSeaShellOneLowerDtoById(Long id) {
        return client.get()
                .uri("/seashells/one/lower/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellLowerDto.class)
                .block();
    }

    public SeaShellAccountDto getSeaShellOneAccountDtoById(String id) {
        return client.get()
                .uri("/seashells/one/account/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellAccountDto.class)
                .block();
    }
}
