package org.findem.findem.User;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    
    private String name;
    private String username;
    private String password;

    // need a test to check if user name and password are unique
    public User(long uid, String name, String username, String password) {
		this.uid = uid;
		this.name = name;
		this.setUsername(username);
		this.password = password;	
	}
    
    public User() {}
    
    public long getUid() {
    	return uid;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void changeName(String name) {
    	this.name = name;
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

