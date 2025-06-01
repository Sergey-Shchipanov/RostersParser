package com.sshchipanov.parser.web;

import com.sshchipanov.parser.model.BCPTournamentList;
import reactor.core.publisher.Flux;

public interface TournamentPortalParser {

    Flux<BCPTournamentList> parseRosters();

}
