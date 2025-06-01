package com.sshchipanov.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@AllArgsConstructor
@Data
public class BCPTournamentList {

    private Faction faction;
    private String playerName;
    private String bcpListUrl;
    private int[] resultRecord;
    private double numberOfWins;
    private String tournamentId;

    public BCPTournamentList(Faction faction, String playerName, String bcpListUrl, ArrayList<Integer> resultRecord, double totalNumWins, String eventId) {
        this.faction = faction;
        this.playerName = playerName;
        this.bcpListUrl = bcpListUrl;
        this.resultRecord = resultRecord.stream().mapToInt(i -> i).toArray();
        this.numberOfWins = totalNumWins;
        this.tournamentId = eventId;
    }
}
