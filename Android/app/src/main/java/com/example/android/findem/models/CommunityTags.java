package com.example.android.findem.models;

public class CommunityTags {
    private long tid;
    
    private long cid;
    private String tag;
    
    public CommunityTags(long cid, String tag) {
    	
    	this.cid = cid;
    	this.tag = tag;
    }

	public long getTid() {
		return tid;
	}

	public long getCid() {
		return cid;
	}

	public String getTag() {
		return tag;
	}

    
}
