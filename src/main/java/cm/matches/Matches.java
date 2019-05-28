package cm.matches;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mid;
    
    private long uid1;
    private long uid2;
    
    private Timestamp tstamp;
    
    public Matches(long uid1, long uid2, Timestamp tstamp) {
    	
    	this.uid1 = uid1;
    	this.uid2 = uid2;
    	this.tstamp = tstamp;
    }

	public long getMid() {
		return mid;
	}

	public long getUid1() {
		return uid1;
	}

	public long getUid2() {
		return uid2;
	}

	public Timestamp getTstamp() {
		return tstamp;
	}

}
