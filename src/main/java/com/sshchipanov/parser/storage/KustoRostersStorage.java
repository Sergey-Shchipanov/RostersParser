package com.sshchipanov.parser.storage;

import com.sshchipanov.parser.model.BCPRoster;
import com.sshchipanov.parser.model.TournamentRoster;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class KustoRostersStorage implements TournamentListsStorage{

    private String connectionString;

    @Override
    public void saveLists(List<TournamentRoster> rosters) {
        log.info("Saving {} rosters to Kusto with connection string: {}", rosters.size(), connectionString);
    }
}
