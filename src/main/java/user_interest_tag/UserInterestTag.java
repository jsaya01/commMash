package user_interest_tag;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserInterestTag {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long tid;

	 private long uid;
	 private String tag;
	 
	 public UserInterestTag(long uid, String tag) {
		 this.uid = uid;
		 this.tag = tag;
	 }

	public long getTid() {
		return tid;
	}

	public long getUid() {
		return uid;
	}

	public String getTag() {
		return tag;
	}
	 
}
