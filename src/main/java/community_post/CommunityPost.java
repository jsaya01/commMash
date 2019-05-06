package community_post;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommunityPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;
    
    private long uid;
    private long cid;
    private Date tstamp;
    private String content;
    
    public CommunityPost(long uid, long cid, Date tstamp, String content) {
    	
    	this.uid = uid;
    	this.cid = cid;
    	this.tstamp = tstamp;
    	this.content = content;
    }

	public long getPid() {
		return pid;
	}

	public long getUid() {
		return uid;
	}

	public long getCid() {
		return cid;
	}

	public Date getTstamp() {
		return tstamp;
	}

	public String getContent() {
		return content;
	}    
    
}
