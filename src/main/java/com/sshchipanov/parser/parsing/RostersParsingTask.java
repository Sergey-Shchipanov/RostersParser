package com.sshchipanov.parser.parsing;

import com.sshchipanov.parser.model.BCPTournamentList;
import com.sshchipanov.parser.storage.TournamentListsStorage;
import com.sshchipanov.parser.web.TournamentPortalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RostersParsingTask {

    private TournamentPortalParser portalParser;
    private TournamentListsStorage storage;

    @Autowired
    public RostersParsingTask(TournamentPortalParser tournamentPortalParser, TournamentListsStorage storage) {
        this.portalParser = tournamentPortalParser;
        this.storage = storage;
    }

    @Scheduled(fixedDelay = 100) // Adjust the interval as needed
    public void parseAndSaveRosters() {
        List<BCPTournamentList> rosters = portalParser.parseRosters();
        storage.saveLists(rosters);
    }
}