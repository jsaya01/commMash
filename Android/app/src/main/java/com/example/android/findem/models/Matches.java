package com.example.android.findem.Models;

import java.sql.Date;

public class Matches {
    private long mid;
    
    private long uid1;
    private long uid2;
    
    private Date tstamp;
    
    public Matches(long uid1, long uid2, Date tstamp) {
    	
    	this.uid1 = uid1;
    	this.uid2 = uid2;
    	this.tstamp = tstamp;
    }

	public long getMid() {
		return mid;
	}

	public long getUid1() {
		return uid1;
	}

	public long getUid2() {
		return uid2;
	}

	public Date getTstamp() {
		return tstamp;
	}

}
