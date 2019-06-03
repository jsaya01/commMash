package cm.matches;

import java.io.DataOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Matches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mid;
    
    private long uid1;
    private long uid2;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date tstamp;
    
    public Matches(long uid1, long uid2) throws ParseException {
    	
    	this.uid1 = uid1;
    	this.uid2 = uid2;
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date temp = sdf1.parse(sdf1.format(date));
    	this.tstamp = temp;
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

	public Date getTstamp() {
		return tstamp;
	}

}
