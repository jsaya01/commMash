package com.example.android.findem.models;

import java.sql.Timestamp;
public class Matches {

    private long uid1;
    private long uid2;
    private Timestamp tstamp;

    public Matches(long uid1, long uid2, Timestamp tstamp) {
    	this.uid1 = uid1;
    	this.uid2 = uid2;
    	this.tstamp = tstamp;
    }

    public long getUid1() {
        return uid1;
    }

    public long getUid2() {
        return uid2;
    }

    public Timestamp getTstamp() {
        return tstamp;
    }
}

