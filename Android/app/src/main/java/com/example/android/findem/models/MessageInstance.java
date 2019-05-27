package com.example.android.findem.models;

public class MessageInstance {
	 private long iid;
	 
	 private long uidfrom;
	 private long uidto;
	 private String content;
	 
	 public MessageInstance(long uidfrom, long uidto, String content) {
		 this.uidfrom = uidfrom;
		 this.uidto = uidto;
		 this.content = content;
	 }

	public long getIid() {
		return iid;
	}

	public long getUidfrom() {
		return uidfrom;
	}

	public long getUidto() {
		return uidto;
	}

	public String getContent() {
		return content;
	}

}
