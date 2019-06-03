package cm.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    
    private String fname;
    private String lname;
    private String username;
    private String password;
    private String description;
    private String imagepath;


    public User(String fname, String lname, String username, String password, String description, String imagepath) {
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
	
	public String getImagepath() {
		return this.imagepath;
	}

}

