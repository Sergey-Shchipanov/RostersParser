package com.sshchipanov.parser.web;

import com.sshchipanov.parser.model.TournamentRoster;

import java.util.List;

public interface TournamentPortalParser {

    public List<TournamentRoster> parseRosters();

}
