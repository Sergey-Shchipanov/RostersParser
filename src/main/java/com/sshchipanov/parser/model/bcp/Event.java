package com.sshchipanov.parser.model.bcp;

import lombok.Data;

import java.util.Date;

@Data
public class Event{
    public Date eventDate;
    public Date eventEndDate;
    public boolean hidePlacings;
    public String name;
    public String id;
}
