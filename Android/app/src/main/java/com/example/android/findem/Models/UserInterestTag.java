package com.example.android.findem.Models;

public class UserInterestTag {
	 private long tid;

	 private long uid;
	 private String tag;
	 
	 public UserInterestTag(long uid, String tag) {
		 this.uid = uid;
		 this.tag = tag;
	 }

	public long getTid() {
		return tid;
	}

	public long getUid() {
		return uid;
	}

	public String getTag() {
		return tag;
	}
	 
}
