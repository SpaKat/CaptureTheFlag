package Message;

import java.io.Serializable;
import java.util.ArrayList;

import CaptureTheFlagGame.Gameboard;
import CaptureTheFlagGame.Team;

public class GameInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5097577196900060851L;
	
	
	private ArrayList<Team> teams;
	private Gameboard gameboard;
	public GameInfo(ArrayList<Team> teams, Gameboard gameboard) {
		this.teams = teams;
		this.gameboard = gameboard;
	}
	
	public ArrayList<Team> getTeams() {
		return teams;
	}
	public double getGameBoardsX(){
		return gameboard.getX();
	}
	public double getGameBoardsY(){
		return gameboard.getY();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return teams.size() + " size " + gameboard.getX() + " X " + gameboard.getY() + " Y "  ;
	}
}
