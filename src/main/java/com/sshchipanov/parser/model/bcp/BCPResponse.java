package com.sshchipanov.parser.model.bcp;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BCPResponse {
    public ArrayList<Datum> data;
    public String nextKey;
    public int numEvents;
    public boolean lastPage;
}
