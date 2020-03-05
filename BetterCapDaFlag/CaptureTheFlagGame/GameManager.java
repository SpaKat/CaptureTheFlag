package CaptureTheFlagGame;

public class GameManager{

	private Gameboard Gameboard;
	private Game game;

	public GameManager(Gameboard gameboard,Game game) {
		Gameboard = gameboard;
		this.game = game;
	}

	public boolean OneTurn() {
		//spawn
		game.spawnPlayers();
		// check for kill & kill
		game.checkForKill();
		//check for point
		game.checkForPoint();
		// move
		game.move();
		// respawn
		game.respawn();
		// clean up
		game.cleanUp();
		// check for win
		return game.checkForWin();
	}

	public void setheight(double newHieght) {
		Gameboard.setY(newHieght);
		Calculations.setY(newHieght);

	}
	public void setwidth(double newWidth) {
		Gameboard.setX(newWidth);
		Calculations.setX(newWidth);

	}
	public void relocatePieces() {
		game.relocateFlags();
		game.relocateBases();
	}


	// ----------------------------------------- NEEDED FOR GUI ----------------------------------//
	public Team[] getTeams() {
		return game.getTeams();
	}
	public boolean addPlayer(Player player, int teamid) {
		return game.getTeam(teamid).addPlayer(player);

	}
	// ----------------------------------------- NEEDED FOR INTERNET ----------------------------------//
	
	public Game getGame() {
		return game;
	}
	 

	






}
