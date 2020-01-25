package CaptureTheFlagGame;

import java.io.Serializable;

public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -365361190277582864L;
	private Team teams[];
	private long respawnTimer = 3000; // in millis seconds
	private Gameboard gameboard;
	private int winningScore = 1;
	
	public Game(Gameboard gameboard, int numOfTeams, int numOfPlayers, int respawnTime) {
		this.gameboard = gameboard;
		setMaxNumofTeams(numOfTeams);
		setnumberofplayers(numOfPlayers);
		setRespawnTimer(respawnTime);
	}
	
	public void spawnPlayers() {
		for (int i = 0; i < teams.length; i++) {
			teams[i].spawnPlayers(gameboard);
		}
	}

	public void checkForKill() {
		//check for bullet hit
		for (int i = 0; i < teams.length; i++) {
			for (int j = i+1; j < teams.length; j++) {
				teams[i].checkBullets(teams[j]);
			}
		}
		// check for dies players
		for (int i = 0; i < teams.length; i++) {
			teams[i].checkForDiedPlayers();
		}
	}

	public void checkForPoint() {
		// TODO Auto-generated method stub

	}

	public void move() {
		// TODO Auto-generated method stub
		//move bullets
		//move players
		

	}

	public boolean checkForWin() {
		boolean winner = false;
		for (int i = 0; i < teams.length; i++) {
			if (teams[i].getScore() >= winningScore ) {
				winner = true;
			}
		}
		return winner;
	}

	public void respawn() {
		for (int i = 0; i < teams.length; i++) {
			teams[i].respawnPlayers(gameboard,respawnTimer);
		}
	}

	public void setnumberofplayers(int num) {
		for (int i = 0; i < teams.length; i++) {
			teams[i].setMaxPlayers(num);
		}
	}

	public void setMaxNumofTeams(int num) {
		teams = new Team[num];
		for (int i = 0; i < num; i++) {
			Team team = new Team(i);
			gameboard.spawnGameColorObject(team.getHomeBase(),team.getId(),0);
			teams[i] = team;
		}
	}

	public void setRespawnTimer(int respawnTimer) {
		this.respawnTimer = respawnTimer;
	}
	public long getRespawnTimer() {
		return respawnTimer;
	}

	public void relocateBases() {
		for (int i = 0; i < teams.length; i++) {
			Team team = teams[i];
			gameboard.spawnGameColorObject(team.getHomeBase(),team.getId(),0);
		}
	}
	public int numOfTeams() {
		return teams.length;
	}

	public Team getTeam(int i) {
		return teams[i];
	}
	public Team[] getTeams() {
		return teams;
	}
	public Gameboard getGameboard() {
		return gameboard;
	}
	public void setWinningScore(int winningScore) {
		this.winningScore = winningScore;
	}
	public int getWinningScore() {
		return winningScore;
	}
	public void relocateFlags() {
		for (int i = 0; i < teams.length; i++) {
			Team team = teams[i];
			if(team.getFlag().isSpawned()){
				//TODO
			}else {
				gameboard.spawnGameColorObject(team.getFlag(),team.getId(),team.getHomeBase().getRadius());
				team.getFlag().setSpawned(true);
			}
		}
	}

}
