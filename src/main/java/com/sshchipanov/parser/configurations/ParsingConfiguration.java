package com.sshchipanov.parser.configurations;

import com.sshchipanov.parser.parsing.RostersParsingTask;
import com.sshchipanov.parser.storage.TournamentListsStorage;
import com.sshchipanov.parser.web.TournamentPortalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParsingConfiguration {

    @Autowired
    TournamentPortalParser tournamentPortalParser;

    @Autowired
    TournamentListsStorage tournamentListsStorage;

    @Bean
    public RostersParsingTask rostersParsingTask() {
        return new RostersParsingTask(tournamentPortalParser, tournamentListsStorage);
    }
}
