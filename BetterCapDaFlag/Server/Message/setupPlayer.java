package Server.Message;

import java.io.Serializable;

import CaptureTheFlagGame.Statistics;

public class setupPlayer extends Message implements Serializable {

	private Statistics stats; 
	private int teamID;
	private String name;
	public setupPlayer(long id,Statistics stats, int teamID, String name) {
		super(id);
		this.stats = stats;
		this.teamID = teamID;
		this.name = name;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1161099742360782162L;
	public Statistics getStats() {
		return stats;
	}
	public int getTeamID() {
		return teamID;
	}
	public String getName() {
		return name;
	}
}
