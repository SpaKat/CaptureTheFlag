package CaptureTheFlagGame;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -365361190277582864L;
	private Team teams[];
	private int respawnTimer = 3000; // in millis seconds
	private Gameboard gameboard;
	
	public Game(Gameboard gameboard, int numOfTeams, int numOfPlayers, int respawnTime) {
		this.gameboard = gameboard;
		setMaxNumofTeams(numOfTeams);
		setnumberofplayers(numOfPlayers);
		setRespawnTimer(respawnTime);
	}
	
	public void spawnPlayers() {
		for (int i = 0; i < teams.length; i++) {
			teams[i].spawnPlayers();
		}
	}

	public void checkForKill() {
		// TODO Auto-generated method stub


	}

	public void checkForPoint() {
		// TODO Auto-generated method stub

	}

	public void move() {
		// TODO Auto-generated method stub

	}

	public boolean checkForWin() {
		// TODO Auto-generated method stub

		return false;
	}

	public void respawn() {
		// TODO Auto-generated method stub

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
			gameboard.spawnHomeBase(team.getHomeBase(),team.getId());
			teams[i] = team;
		}
	}

	public void setRespawnTimer(int respawnTimer) {
		this.respawnTimer = respawnTimer;
	}
	public int getRespawnTimer() {
		return respawnTimer;
	}

	public void relocateBases() {
		for (int i = 0; i < teams.length; i++) {
			Team team = teams[i];
			gameboard.spawnHomeBase(team.getHomeBase(),team.getId());
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
	public void relocateFlags() {
		for (int i = 0; i < teams.length; i++) {
			Team team = teams[i];
			if(team.getFlag().isSpawned()){
				//TODO
			}else {
				gameboard.spawnFlags(team.getFlag(),team.getId(),team.getHomeBase().getRadius());
				team.getFlag().setSpawned(true);
			}
		}
	}

}
