package com.sshchipanov.parser.model.bcp;

import lombok.Data;

import java.util.Date;

@Data
public class Datum{
    public String id;
    public Date created_at;
    public Date updated_at;
    public String playerId;
    public String eventId;
    public Player player;
    public Event event;
}
