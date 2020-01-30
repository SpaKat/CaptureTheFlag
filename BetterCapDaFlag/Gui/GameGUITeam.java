package Gui;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Team;

public class GameGUITeam {

	private Team team;
	private GameGUI gamegui;
	private GameGUIHomeBase homebase;
	private GameGUIFlag flag;
	private GameGUIPlayer players[];
	private int playerCount = 0;

	public GameGUITeam(Team team, GameGUI gamegui) {
		this.team = team;
		this.gamegui = gamegui;
		homebase = new GameGUIHomeBase(team.getHomeBase());
		flag = new GameGUIFlag(team.getFlag());

		gamegui.getChildren().addAll(homebase,flag);

		players = new GameGUIPlayer[team.getPlayers().length];
		for (int i = 0; i < players.length; i++) {
			players[i] = new GameGUIPlayer();
		}

	}

	public void relocate() {
		homebase.relocatePOS();
		flag.relocatePOS();
	}

	public void updatePlayers() {
		Player[] backPlayers = team.getPlayers();
		for (int j = 0; j < backPlayers.length; j++) {
			if(backPlayers[j] != null ) {
				if (players[j].getPlayer() != null) {
					if (players[j].getPlayer().same(backPlayers[j])) {
						// gui player has the back ground player
						players[j].relocatePOS();
					} 
				}else {
					//gui does not have the background player
					players[j].setup(backPlayers[j]);
				}
				//System.out.println(gamegui.getChildren().size());
				if (!gamegui.getChildren().contains(players[j])) {
					gamegui.getChildren().add(players[j]);
				}
			}else {
				gamegui.getChildren().remove(players[j]);
			}

		}
	}
	public void updateBullets() {

	}
}
