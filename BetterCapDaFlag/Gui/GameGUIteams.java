package Gui;
import java.util.ArrayList;

import CaptureTheFlagGame.Team;

public class GameGUIteams {
	
	private Team team;
	private GameBoardPane gameBoardPane;
	private GameGUIHomeBase homebase;
	private GameGUIFlag flag;
	private ArrayList<GameGUIPlayers> players;
	
	
	public GameGUIteams(Team team, GameBoardPane gameBoardPane) {
		this.team = team;
		this.gameBoardPane = gameBoardPane;
		homebase = new GameGUIHomeBase(team.getHomeBase());
		flag = new GameGUIFlag(team.getFlag());
		players = new ArrayList<>();
		
		gameBoardPane.getChildren().addAll(homebase,flag);
	}

	public void relocate() {
		homebase.relocatePOS();
		flag.relocatePOS();
		for (int i = 0; i < players.size(); i++) {
			GameGUIPlayers player = players.get(i);
			player.relocatePOS();
		}
	}

	public void addPlayers() {
		for (int j = 0; j < team.getPlayers().size(); j++) {
			GameGUIPlayers player = new GameGUIPlayers(team.getPlayers().get(j));
			if (!players.contains(player)) {
				players.add(player);
			} 
		}
	}

	
	
	
	
}
