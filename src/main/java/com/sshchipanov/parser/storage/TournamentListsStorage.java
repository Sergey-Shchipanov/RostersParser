package com.sshchipanov.parser.storage;

import com.sshchipanov.parser.model.TournamentRoster;

import java.util.List;

public interface TournamentListsStorage {

    void saveLists(List<TournamentRoster> rosters);
}
