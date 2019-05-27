package com.example.android.findem.Models;

public class CommunityUserProfile {
	private long uid;
	private long cid;
	private String description;
	
	public CommunityUserProfile(long uid, long cid, String description) {
		this.uid = uid;
		this.cid = cid;
		this.description = description;
	}
	
	public long getUid() {
		return uid;
	}

	public long getCid() {
		return cid;
	}

	public String getDescription() {
		return description;
	}

	
}
