package com.sshchipanov.parser.model.bcp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Player{
    public String firstName;
    public String lastName;
    public String armyName;
    public int battlePoints;
    public double numWins;
    @JsonProperty("record")
    public List<Integer> myrecord;
    public List<Integer> resultRecord;
    @JsonProperty("total_battlePoints")
    public int totalBattlePoints;
    @JsonProperty("total_numWins")
    public double totalNumWins;
    @JsonProperty("total_record")
    public List<Integer> totalRecord;
    @JsonProperty("total_resultRecord")
    public List<Integer> totalResultRecord;
    public String id;
    public String armyListObjectId;
    public String teamName;
}