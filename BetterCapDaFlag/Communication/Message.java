package Communication;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9035665000476997025L;

	private int ID;
	// player 1
	
	
	public Message(int id) {
		ID = id;
	}
	
	public int getID() {
		return ID;
	}

	
}
