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

    // need a test to check if cm.user name and password are unique
    public User(String fname, String lname, String username, String password, String description) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;	
		this.description = description;
	}
    
    public User() {}
    
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

