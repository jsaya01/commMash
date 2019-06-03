package com.example.android.findem.models;

public class Matches {

    private long uid1;
    private long uid2;

    public Matches(long uid1, long uid2) {
    	this.uid1 = uid1;
    	this.uid2 = uid2;
    }

    public long getUid1() {
        return uid1;
    }

    public long getUid2() {
        return uid2;
    }

}

