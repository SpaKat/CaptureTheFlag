package Message;

import java.io.Serializable;

import CaptureTheFlagGame.Statistics;

public class GameTeamSelect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4509075825676580262L;
	private int teamId;
	private Statistics stats;
	public GameTeamSelect(int teamId,Statistics stats) {
		this.teamId = teamId;
		this.stats = stats;
	}
	public int getTeamId() {
		return teamId;
	}
	public Statistics getStats() {
		return stats;
	}
}
