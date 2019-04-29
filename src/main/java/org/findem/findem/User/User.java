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
		this.username = username;
		this.password = password;	
	}
    
    public User() {}
    
    public long getUid() {
    	return uid;
    }
    
    public String getName() {
    	return name;
    }
    
    public void changeName(String name) {
    	this.name = name;
    }
    
    public void updateUsername(String userName) {
    	this.username = userName;
    }
    
    public void changePassword(String passWord) {
    	this.password = passWord;
    }

}

