package com.example.android.findem.Models;

import java.sql.Date;

public class CommunityPost {
    private long pid;
    
    private long uid;
    private long cid;
    private Date tstamp;
    private String content;
    
    public CommunityPost(long uid, long cid, Date tstamp, String content) {
    	
    	this.uid = uid;
    	this.cid = cid;
    	this.tstamp = tstamp;
    	this.content = content;
    }

	public long getPid() {
		return pid;
	}

	public long getUid() {
		return uid;
	}

	public long getCid() {
		return cid;
	}

	public Date getTstamp() {
		return tstamp;
	}

	public String getContent() {
		return content;
	}    
    
}
