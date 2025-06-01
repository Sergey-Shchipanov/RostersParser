package com.sshchipanov.parser.model.bcp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Player{
    public String firstName;
    public String lastName;
    public String armyName;
    public int battlePoints;
    public double numWins;
    @JsonProperty("record")
    public ArrayList<Integer> myrecord;
    public ArrayList<Integer> resultRecord;
    public int total_battlePoints;
    public double total_numWins;
    public ArrayList<Integer> total_record;
    public ArrayList<Integer> total_resultRecord;
    public String id;
    public String armyListObjectId;
    public String teamName;
}