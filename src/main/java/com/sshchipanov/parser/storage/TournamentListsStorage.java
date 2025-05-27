package com.sshchipanov.parser.storage;

import com.sshchipanov.parser.model.BCPRoster;

import java.util.List;

public interface TournamentListsStorage {

    void saveLists(List<BCPRoster> rosters);
}
