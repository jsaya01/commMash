package community_tags;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommunityTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tid;
    
    private long cid;
    private String tag;
    
    public CommunityTags(long cid, String tag) {
    	
    	this.cid = cid;
    	this.tag = tag;
    }

	public long getTid() {
		return tid;
	}

	public long getCid() {
		return cid;
	}

	public String getTag() {
		return tag;
	}

    
}
