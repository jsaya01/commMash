package com.example.android.findem.models;

import com.example.android.findem.utils.StringGenerator;

import java.io.Serializable;
import java.sql.Timestamp;

public class Match implements Serializable {
    private String fname;
    private Timestamp dt;
    private String lastMessage;
    private String imagepath;
    private String description;

    public Match(String fname, Timestamp dt, String imagepath, String description) {
        this.fname = fname;
        this.dt = dt;
        this.imagepath = imagepath;
        this.lastMessage = StringGenerator.getRandomMessage();
        this.description = description;
    }

    public String getDescription() {
        return description;
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
