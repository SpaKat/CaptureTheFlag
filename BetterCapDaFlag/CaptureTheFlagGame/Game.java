package CaptureTheFlagGame;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<Team> teams;
	private int respawnTimer = 3; // in seconds
	private Gameboard gameboard;
	public Game(Gameboard gameboard) {
		this.gameboard = gameboard;
		teams = new ArrayList<>();
	}

	public void spawnPLayers() {
		// TODO Auto-generated method stub
		
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

	public void setnumberofplayers(int parseInt) {
		for (int i = 0; i < teams.size(); i++) {
			teams.get(i).setMaxPlayers(parseInt);
		}
	}

	public void setMaxNumofTeams(int parseInt) {
		teams.clear();
		for (int i = 0; i < parseInt; i++) {
			Team team = new Team(i);
			gameboard.spawnHomeBase(team.getHomeBase(),team.getId());
			teams.add(team);
		}
	}

	public void setRespawnTimer(int respawnTimer) {
		this.respawnTimer = respawnTimer;
	}
	public int getRespawnTimer() {
		return respawnTimer;
	}

	public void relocateBases() {
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			gameboard.spawnHomeBase(team.getHomeBase(),team.getId());
		}
	}
	public int numOfTeams() {
		return teams.size();
	}

	public Team getTeam(int i) {
		return teams.get(i);
	}
	
}
