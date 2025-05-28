package com.sshchipanov.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BCPTournamentList {

    private Faction faction;
    private Detachment detachment;
    private String playerName;
    private String bcpListUrl;
    private String tournamentPath;
    private int umberOfWins;
    private String tournamentId;

}
