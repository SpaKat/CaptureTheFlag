package Gui;
import java.util.ArrayList;

import CaptureTheFlagGame.Team;

public class GameGUITeam {
	
	private Team team;
	private GameGUI gamegui;
	private GameGUIHomeBase homebase;
	private GameGUIFlag flag;
	private ArrayList<GameGUIPlayer> players;
	
	
	public GameGUITeam(Team team, GameGUI gamegui) {
		this.team = team;
		this.gamegui = gamegui;
		homebase = new GameGUIHomeBase(team.getHomeBase());
		flag = new GameGUIFlag(team.getFlag());
		
		gamegui.getChildren().addAll(homebase,flag);
		
		players = new ArrayList<>();
		
	}

	public void relocate() {
		homebase.relocatePOS();
		flag.relocatePOS();
		players.forEach(p->p.relocatePOS());
	}

	public void addPlayers() {
		for (int j = 0; j < team.getPlayers().length; j++) {
			GameGUIPlayer player = new GameGUIPlayer(team.getPlayers()[j]);
			if (!players.contains(player)) {
				players.add(player);
				gamegui.getChildren().add(player);
			} 
		}
	}

	
	
	
	
}
