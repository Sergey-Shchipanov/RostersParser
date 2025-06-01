package com.sshchipanov.parser;

import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import com.sshchipanov.parser.model.BCPTournamentList;
import com.sshchipanov.parser.storage.TournamentListsStorage;
import com.sshchipanov.parser.web.TournamentPortalParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class RostersParsingTask {

    private TournamentPortalParser portalParser;
    private TournamentListsStorage storage;

    @Autowired
    public RostersParsingTask(TournamentPortalParser tournamentPortalParser, TournamentListsStorage storage) {
        this.portalParser = tournamentPortalParser;
        this.storage = storage;
    }

    public void parseAndSaveRosters() {
        Flux<BCPTournamentList> rosters = portalParser.parseRosters();
        storage.saveLists(rosters);
    }

    @FunctionName("RostersParser")
    public void timerHandler(
            @TimerTrigger(name = "timerInfo", schedule = "*/10 * * * * *") String timerInfo){
        log.info("Java Timer trigger function executed at: {}", LocalDateTime.now());
        parseAndSaveRosters();
    }
}