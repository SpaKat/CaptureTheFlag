package Gui;
import java.util.ArrayList;

import CaptureTheFlagGame.Bullet;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Team;

public class GameGUITeam {

	private Team team;
	private GameGUI gamegui;
	private GameGUIHomeBase homebase;
	private GameGUIFlag flag;
	private GameGUIPlayer players[];
	private GameGUIBullet bullets[][];

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

		bullets = new GameGUIBullet[team.getPlayers().length ][ team.MaxBullets()];
		for (int i = 0; i < bullets.length; i++) {
			for (int j = 0; j < bullets[i].length; j++) {
				bullets[i][j] = new GameGUIBullet();
			}
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
					}else {
						//gui does not have the right  background player
						players[j].setup(backPlayers[j]);
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
		Player[] backPlayers = team.getPlayers();
		for (int i = 0; i < bullets.length; i++) {
			for (int j = 0; j < bullets[i].length; j++) {
				if (backPlayers[i] != null) {
					Bullet[] backBullets = backPlayers[i].getBullets();
					if (backBullets[j] != null) {
						//have bullet
						if (bullets[i][j].getBullet() != null) {
							if (bullets[i][j].getBullet().same(backBullets[j])) {
								bullets[i][j].relocatePOS();
							}else {
								bullets[i][j].setup(backBullets[j]);
							}
						}else {
							bullets[i][j].setup(backBullets[j]);
						}
					}else {
						gamegui.getChildren().remove(bullets[i][j]);
					}
					if (!gamegui.getChildren().contains(bullets[i][j])) {
						gamegui.getChildren().add(bullets[i][j]);
					}
				}else {
					gamegui.getChildren().remove(bullets[i][j]);
				}
			}
		}
	}
}
