package cm.community;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cid;
    
    private String name;
    private String imagepath;
    private String description;
    
    public Community(String name, String imagepath, String description) {
    	this.name = name;
    	this.imagepath = imagepath;
    	this.description = description;
    }
    
    public long getCid() {
		return cid;
	}
    
    public String getDescription() {
		return description;
	}
    
    public String getImagepath() {
		return imagepath;
	}
    
    public String getName() {
		return name;
	}
}
