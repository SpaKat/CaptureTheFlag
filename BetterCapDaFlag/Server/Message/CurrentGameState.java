package Server.Message;

import CaptureTheFlagGame.Game;

public class CurrentGameState extends Message {

	private Game game;
	
	public CurrentGameState(long id,Game g) {
		super(id);
		this.game = g;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7212854071143437772L;
	public Game getGame() {
		return game;
	}
}
