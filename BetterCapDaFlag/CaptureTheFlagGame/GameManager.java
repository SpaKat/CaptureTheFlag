package CaptureTheFlagGame;

import Message.GameInfo;

public class GameManager{

	private Gameboard Gameboard;
	private Game game;

	public GameManager(double x,double y) {
		Gameboard = new Gameboard(x, y);
		game = new Game(Gameboard);
	}

	public boolean OneTurn() {
		//spawn
		game.spawnPLayers();
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
	public void setMaxNumofTeams(int parseInt) {
		game.setMaxNumofTeams(parseInt);
	}
	public void setMaxPLayersPerTeam(int parseInt) {
		game.setnumberofplayers(parseInt);
	}
	public void setRespawnTimer(int parseInt) {
		game.setRespawnTimer(parseInt);
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

	public GameInfo sendInfo() {
		GameInfo gameInfo = new GameInfo(game.getTeams(),game.getGameboard()); 
		return gameInfo;
	}





}
