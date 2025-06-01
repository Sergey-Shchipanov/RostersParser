package com.sshchipanov.parser.storage;

import com.sshchipanov.parser.model.BCPTournamentList;
import reactor.core.publisher.Flux;

import java.util.List;

public interface TournamentListsStorage {

    void saveLists(Flux<BCPTournamentList> rosters);
}
