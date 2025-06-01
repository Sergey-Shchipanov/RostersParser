package com.sshchipanov.parser.model.bcp;

import lombok.Data;

import java.util.List;

@Data
public class BCPResponse {
    public List<Datum> data;
    public String nextKey;
    public int numEvents;
    public boolean lastPage;
}
