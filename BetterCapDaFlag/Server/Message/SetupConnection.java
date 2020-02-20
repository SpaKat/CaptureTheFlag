package Server.Message;

import java.io.Serializable;

public class SetupConnection implements Serializable {

	private long id;
	private int teams;
	public SetupConnection(long id, int numOfteams) {
		this.id = id;
		teams = numOfteams;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 972622375588748345L;

	
	public long getId() {
		return id;
	}
	public int getTeams() {
		return teams;
	}
	
}
