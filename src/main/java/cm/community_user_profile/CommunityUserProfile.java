package cm.community_user_profile;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CUP_CompKeys.class)
public class CommunityUserProfile{

    @Id
	private long uid;
    @Id
	private long cid;
    
	private String description;
	
	public CommunityUserProfile() {
		
	}
	
	public CommunityUserProfile(long uid, long cid, String description) {
		this.uid = uid;
		this.cid = cid;
		this.description = description;
	}
	
	public long getUid() {
		return uid;
	}

	public long getCid() {
		return cid;
	}

	public String getDescription() {
		return description;
	}

	
}
