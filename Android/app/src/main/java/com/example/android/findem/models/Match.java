package com.example.android.findem.models;

import com.example.android.findem.utils.StringGenerator;

import java.io.Serializable;
import java.sql.Timestamp;

public class Match implements Serializable {
    private long uid;
    private String fname;
    private Timestamp dt;
    private String lastMessage;
    private String imagepath;
    private String description;

    public Match(long uid, String fname, Timestamp dt, String imagepath, String description) {
        this.uid = uid;
        this.fname = fname;
        this.dt = dt;
        this.imagepath = imagepath;
        this.lastMessage = StringGenerator.getRandomMessage();
        this.description = description;
    }

    public long getUid() {
        return uid;
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
