package com.sshchipanov.parser.web;

import com.sshchipanov.parser.model.BCPTournamentList;
import com.sshchipanov.parser.model.Faction;
import com.sshchipanov.parser.model.bcp.BCPResponse;
import com.sshchipanov.parser.model.bcp.Datum;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class BCPListsParser implements TournamentPortalParser {

    public static final String LIST_URL_BASE = "https://www.bestcoastpairings.com/list/";
    //    public static final String LISTS_URL_BASE = "https://newprod-api.bestcoastpairings.com/v1/tournaments";
    public static final String LISTS_URL_BASE = "https://rostersparser.free.beeceptor.com/lists";
    private String bcpListsUrl;

    @Override
    public Flux<BCPTournamentList> parseRosters() {
        log.info("Starting to parse rosters from BCP at URL: {}", StringUtils.isEmpty(bcpListsUrl) ? LIST_URL_BASE : bcpListsUrl);
        String authenticationToken = getAuthenticationToken();
        log.info("Generated authentication token: {}", authenticationToken);
        WebClient webClient = createWebClient(bcpListsUrl);
        Mono<BCPResponse> response = webClient.get().header("Authorization", "Bearer " + authenticationToken)
                .attribute("limit", "100")
                .attribute("startDate", "2025-05-24T21")
                .attribute("endDate", "2025-06-01T21")
                .attribute("gameType", "1")
                .retrieve()
                .onStatus(status -> status.value() != 200, clientResponse -> {
                    log.error("Failed to retrieve data from BCP: {}", clientResponse.statusCode());
                    return Mono.error(new IllegalStateException("Failed to retrieve data from BCP"));
                })
                .bodyToMono(BCPResponse.class);

        return convertResponseToEntities(response);
    }

    protected WebClient createWebClient(String bcpListsUrl) {
        final int size = (int) DataSize.ofMegabytes(16).toBytes();
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        return WebClient.builder().baseUrl(StringUtils.isEmpty(bcpListsUrl) ? LISTS_URL_BASE : bcpListsUrl).exchangeStrategies(strategies).build();

    }

    protected Flux<BCPTournamentList> convertResponseToEntities(Mono<BCPResponse> response) {
        return response
                .flatMapMany(bcpResponse -> Flux.fromIterable(bcpResponse.getData()))
                .map(this::mapDataToTournamentList);
    }

    protected BCPTournamentList mapDataToTournamentList(Datum data) {
        log.info("Converted data: {}", data);
        Faction faction = Faction.valueOfDisplayName(data.getPlayer().getArmyName());
        String playerName = data.getPlayer().firstName + " " + data.getPlayer().getLastName();
        String listUrl = LIST_URL_BASE + data.getPlayer().getArmyListObjectId();
        List<Integer> resultRecord = data.getPlayer().getTotalResultRecord();
        double numWins = data.getPlayer().getTotalNumWins();
        String eventId = data.getEventId();
        BCPTournamentList bcpTournamentList = new BCPTournamentList(faction, playerName, listUrl, resultRecord, numWins, eventId);
        log.info("Converted data: {}", bcpTournamentList);
        return bcpTournamentList;
    }


    protected String getAuthenticationToken() {
        return "eyJraWQiOiJqWDJMamZlWFRIakZhMkVXQW5DZVZFSERIdUFQR29QMUJqUFA1dHZQSlJzPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIzNDQ4NDQ2OC1jMGIxLTcwZmMtMDJhZS1lOWNlZjlkNTJiZjYiLCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAudXMtZWFzdC0xLmFtYXpvbmF3cy5jb21cL3VzLWVhc3QtMV95cHY1bTgyd3ciLCJjbGllbnRfaWQiOiI1MDgzaWloMG5pdHBuNWVubDAyZmtwcjliYyIsIm9yaWdpbl9qdGkiOiI1OGU1ZjgyMS05ZTZhLTQ5NzUtODhkMi1mMDIzMzZlNWJkMzIiLCJldmVudF9pZCI6IjhmNzRmYTBjLTllZWEtNDM2Zi1hOGVkLTc1NzVlNDZkODMzZiIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiYXdzLmNvZ25pdG8uc2lnbmluLnVzZXIuYWRtaW4iLCJhdXRoX3RpbWUiOjE3NDg3NjUyMDQsImV4cCI6MTc0ODc2ODgwNSwiaWF0IjoxNzQ4NzY1MjA1LCJqdGkiOiI2MTdlOWM3OS0xZTlkLTQ3MjItYTJkYi1kNzI0MjA5N2M2ZjAiLCJ1c2VybmFtZSI6IjM0NDg0NDY4LWMwYjEtNzBmYy0wMmFlLWU5Y2VmOWQ1MmJmNiJ9.mtZ7QWGmjMJGP48Luu2TxOPPoIm02vWFQt8q4SbwcTPHwmUvh1dcaswm_PWQ0PclXM-OeSewUdZh_OPsK8Z74SxCGAreKib4x7vGhiT5f9McisTLyp_evM28kB3l6qqynL0JX1CEIB_qeo_qwRP-yplXi6Ee6XQjT3YRHokjK44Mi6EcgIIraoHz5XSxvRPMt1C5EnJfRzDcG5l4Wpsa39GtT0JojKD7wnec2uewBskotcO9NwvnBs8XHIn8c9X0bo75tIFYI5IUFSgas9r-BEWQTC6PvjF_Xag3DLKEJAmIlKsGnKwPnmJadnFejLHPFOBRJfxkyFX664Y51t7BTw";
    }


}
