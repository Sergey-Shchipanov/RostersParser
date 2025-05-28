package com.sshchipanov.parser.web;

import com.sshchipanov.parser.model.BCPTournamentList;

import java.util.List;

public interface TournamentPortalParser {

    List<BCPTournamentList> parseRosters();

}
