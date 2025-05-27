package com.sshchipanov.parser;

import com.sshchipanov.parser.model.TournamentRoster;
import com.sshchipanov.parser.storage.TournamentListsStorage;
import com.sshchipanov.parser.web.TournamentPortalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RostersParsingTask {

    private List<TournamentPortalParser> portalParsers;
    private List<TournamentListsStorage> storages;

    @Autowired
    public RostersParsingTask(List<TournamentPortalParser> portalParsers, List<TournamentListsStorage> storages) {
        this.portalParsers = portalParsers;
        this.storages = storages;
    }

    @Scheduled(fixedDelay = 100) // Adjust the interval as needed
    public void parseAndSaveRosters() {
        for (TournamentPortalParser parser : portalParsers) {
            List<TournamentRoster> rosters = parser.parseRosters();
            for (TournamentListsStorage storage : storages) {
                storage.saveLists(rosters);
            }
        }
    }
}
