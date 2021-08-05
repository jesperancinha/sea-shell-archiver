package org.jesperancinha.shell.webflux.client.future;

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

@Slf4j
public class SeaShellWebClientOneHelper {

    private final WebClient client;

    public SeaShellWebClientOneHelper(WebClient client) {
        this.client = client;
    }

    /**
     * Fetches a list of {@link SeaShellDto} ids and uses that to retrieve the full data using Futures.
     *
     * @param executorService An executor service of our choice of type {@link ExecutorService}
     * @return A list of all {@link SeaShellDto}
     * @throws ExecutionException   Exception thrown by the future on an execution failure
     * @throws InterruptedException Thrown in case of Future interruption
     */
    public List<SeaShellDto> getMultipleRequestClientSideOneShell(final ExecutorService executorService) throws ExecutionException, InterruptedException {
        final var seaShellDtoList = new ArrayList<SeaShellDto>();
        final var shells = executorService.submit(() -> stream(getSeaShellOneAll()).parallel().forEach(seaShellId -> {
            try {
                seaShellDtoList.add(getMultipleRequestClientSideOneShell(seaShellId, executorService));
            } catch (ExecutionException | InterruptedException e) {
                log.error("Error!", e);
            }
        }));
        shells.get();
        return seaShellDtoList;
    }

    public SeaShellDto getMultipleRequestClientSideOneShell(final Long id, final ExecutorService executorService) throws ExecutionException, InterruptedException {
        final SeaShellDto seaShellOneDtoById = getSeaShellOneDtoById(id);
        final Future<?> costumes = executorService.submit(costumesRunnable(seaShellOneDtoById, executorService));
        final Future<?> persons = executorService.submit(personsRunnable(seaShellOneDtoById, executorService));
        costumes.get();
        persons.get();
        return seaShellOneDtoById;
    }

    /**
     * Gathers information about the id of a {@link SeaShellAccountDto} of a {@link SeaShellCostumeDto} from a {@link SeaShellDto} and fetches them from the repository.
     * <p>
     * This is all using {@link Future}.
     *
     * @param seaShellDto
     * @param executorService
     * @return
     */
    private Runnable personsRunnable(SeaShellDto seaShellDto, ExecutorService executorService) {
        return () -> seaShellDto.addPersons(seaShellDto.personIds().parallelStream().map(id ->
        {
            try {
                SeaShellPersonDto seaShellOnePersonDtoById = getSeaShellOnePersonDtoById(id);
                Future<?> costume = executorService.submit(() -> seaShellOnePersonDtoById.setCostumeDto(
                        getCostumeDto(seaShellOnePersonDtoById.getCostumeId(), executorService)));
                Future<?> account = executorService.submit(() -> seaShellOnePersonDtoById.setAccountDto(
                        getSeaShellOneAccountDtoById(seaShellOnePersonDtoById.getAccountId(), executorService)));
                account.get();
                costume.get();
                return seaShellOnePersonDtoById;
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error!", e);
            }
            return null;
        }).collect(Collectors.toList()));
    }

    private Runnable costumesRunnable(SeaShellDto seaShellOneDtoById, ExecutorService executorService) {
        return () -> seaShellOneDtoById.addCostumes(
                seaShellOneDtoById.costumeIds()
                        .parallelStream()
                        .map(id -> this.getCostumeDto(id, executorService))
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

    private SeaShellCostumeDto getCostumeDto(Long id, ExecutorService executorService) {
        try {
            final SeaShellCostumeDto seaShellOneCostumeDtoById = getSeaShellOneCostumeDtoById(id);
            Future<?> tops = executorService.submit(() -> seaShellOneCostumeDtoById.setTopDto(getSeaShellOneTopDtoById(seaShellOneCostumeDtoById.getTopId())));
            Future<?> lowers = executorService.submit(() -> seaShellOneCostumeDtoById.setLowerDto(getSeaShellOneLowerDtoById(seaShellOneCostumeDtoById.getLowerId())));
            tops.get();
            lowers.get();
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

    public SeaShellAccountDto getSeaShellOneAccountDtoById(String id, ExecutorService executorService) {
        return client.get()
                .uri("/seashells/one/account/{id}", id)
                .retrieve()
                .bodyToMono(SeaShellAccountDto.class)
                .block();
    }
}
