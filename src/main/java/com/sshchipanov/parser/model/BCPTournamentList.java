package com.sshchipanov.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class BCPTournamentList {

    private Faction faction;
    private String playerName;
    private String bcpListUrl;
    private List<Integer> resultRecord;
    private double numberOfWins;
    private String tournamentId;
}
