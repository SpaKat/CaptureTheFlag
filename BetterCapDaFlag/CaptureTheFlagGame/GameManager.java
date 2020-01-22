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
		// check for win
		return game.checkForWin();
	}

	public void setheight(double newHieght) {
		Gameboard.setY(newHieght);
	}
	public void setwidth(double newWidth) {
		Gameboard.setX(newWidth);
	}
	public void setMaxNumofTeams(int num) {
		game.setMaxNumofTeams(num);
	}
	public void setMaxPLayersPerTeam(int num) {
		game.setnumberofplayers(num);
	}
	public void setRespawnTimer(int num) {
		game.setRespawnTimer(num);
	}
	public void relocatePieces() {
		game.relocateFlags();
		game.relocateBases();
	}


	// ----------------------------------------- NEEDED FOR GUI ----------------------------------//
	public int getTeams() {
		// TODO Auto-generated method stub
		return game.numOfTeams();
	}

	public Team getTeam(int i) {
		return game.getTeam(i);
	}
	// ----------------------------------------- NEEDED FOR INTERNET ----------------------------------//
/*
	public GameInfo sendInfo() {
		GameInfo gameInfo = new GameInfo(game.getTeams(),game.getGameboard()); 
		return gameInfo;
	}
*/

	



}
