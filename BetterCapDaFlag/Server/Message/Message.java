package Server.Message;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3088980190563968340L;
	
	private long id;
	
	public Message(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	
}
