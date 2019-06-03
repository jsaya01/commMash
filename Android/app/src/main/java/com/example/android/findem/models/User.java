package com.example.android.findem.models;

public class User {
    private long uid;
    
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String description;
    private String imagepath;

	// need a test to check if cm.user name and password are unique
	public User(String fname, String lname, String username, String password, String description) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.description = description;
		this.imagepath = "remove";
	}

    // need a test to check if cm.user name and password are unique
    public User(String fname, String lname, String username, String password, String description, String imagepath) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;	
		this.description = description;
		this.imagepath = imagepath;
	}

	// need a test to check if cm.user name and password are unique
	public User(long uid, String fname, String lname, String username, String password, String description, String imagepath) {
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.description = description;
		this.imagepath = imagepath;
	}

    public User() {}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getImagepath() {
		return imagepath;
	}

	public String getDescription() {
		return description;
	}
    
    public String getFname() {
		return fname;
	}
    
    public String getLname() {
		return lname;
	}
    
    public long getUid() {
    	return uid;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void updateUsername(String userName) {
    	this.setUsername(userName);
    }
    
    public void changePassword(String passWord) {
    	this.password = passWord;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

