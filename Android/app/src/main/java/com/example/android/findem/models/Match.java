package com.example.android.findem.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Match implements Serializable {
    private String fname;
    private Timestamp dt;
    private String lastMessage;
    private String imagepath;

    public Match(String fname, Timestamp dt, String lastMessage, String imagepath) {
        this.fname = fname;
        this.dt = dt;
        this.lastMessage = lastMessage;
        this.imagepath = imagepath;
    }

    public String getFname() {
        return fname;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public Timestamp getDt() {
        return dt;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getImagepath() {
        return imagepath;
    }
}
