package com.sshchipanov.parser.storage;

import com.sshchipanov.parser.model.BCPTournamentList;

import java.util.List;

public interface TournamentListsStorage {

    void saveLists(List<BCPTournamentList> rosters);
}
