package com.sshchipanov.parser.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BCPRoster extends TournamentRoster{

    private String bcpListUrl;

    public BCPRoster(Faction faction, Detachment detachment, String playerName, String roster) {
        super(faction, detachment, playerName, roster);
    }
}
