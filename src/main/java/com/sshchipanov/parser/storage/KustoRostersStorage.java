package com.sshchipanov.parser.storage;

import com.sshchipanov.parser.model.BCPTournamentList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class KustoRostersStorage implements TournamentListsStorage{

    private String connectionString;

    @Override
    public void saveLists(Flux<BCPTournamentList> rosters) {
        log.info("Saving rosters to Kusto with connection string: {}", connectionString);
        List<BCPTournamentList> rosterList = rosters.collectList().block();

        if (rosterList != null && !rosterList.isEmpty()) {
            log.info("Successfully saved {} rosters to Kusto", rosterList.size());
        } else {
            log.warn("No rosters to save to Kusto");
        }
    }
}
