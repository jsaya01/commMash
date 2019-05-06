package message_instance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageInstance {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long iid;
	 
	 private long uidfrom;
	 private long uidto;
	 private String content;
	 
	 public MessageInstance(long uidfrom, long uidto, String context) {
		 this.uidfrom = uidfrom;
		 this.uidto = uidto;
		 this.content = content;
	 }

	public long getIid() {
		return iid;
	}

	public long getUidfrom() {
		return uidfrom;
	}

	public long getUidto() {
		return uidto;
	}

	public String getContent() {
		return content;
	}

}
