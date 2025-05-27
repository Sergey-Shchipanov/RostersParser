package com.sshchipanov.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TournamentRoster {

    private Faction faction;
    private Detachment detachment;
    private String playerName;
    private String roster;

}
