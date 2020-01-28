package JunitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CaptureTheFlagGame.Game;
import CaptureTheFlagGame.Gameboard;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import CaptureTheFlagGame.Team;

class TestGame {

	private Game game;
	private Gameboard gameboard;

	@BeforeEach
	void setUp() throws Exception {
		gameboard = new Gameboard(1000, 1000);
		game = new Game(gameboard,2,20,3000);
		for (int i = 0; i < game.getTeams().length; i++) {
			Team t = game.getTeam(i);
			for (int j = 0; j < 20; j++) {
				t.getPlayers()[j] = new Player(new Statistics(1, 1, 1, 1),1);
			}
		}
	}

	@Test
	void playersSpawninHomeBase() {
		
		game.spawnPlayers();
		
		
		Team[] teams = game.getTeams();
		for (int i = 0; i < teams.length; i++) {
			Player[] players = teams[i].getPlayers();
			for (int j = 0; j < players.length; j++) {

				boolean lessthanHomeRadius = false;
				double dx = Math.abs( players[j].getX() - teams[i].getHomeBase().getX() );
				double dy = Math.abs( players[j].getY() - teams[i].getHomeBase().getY() );
				double distance = Math.sqrt( Math.pow(dx, 2) + Math.pow(dy, 2));
				
				if ( distance <  Math.sqrt(2 * Math.pow(teams[i].getHomeBase().getRadius(),2) ) ) {
					lessthanHomeRadius = true;

				}
				assertTrue(lessthanHomeRadius);
			}
		}

	}
	
	@Test
	void diedPlayers() {
		game.spawnPlayers();
		game.getTeam(0).getPlayers()[0].getStats().takeDamage(1000000);
		game.checkForKill();
		assertEquals(true, game.getTeam(0).getPlayers()[0].isDied());
		
	}
	
	@Test
	void Respawnplayers() {
		
		game.spawnPlayers();
		
		Team[] teams = game.getTeams();
		for (int i = 0; i < teams.length; i++) {
			Player[] players = teams[i].getPlayers();
			for (int j = 0; j < players.length; j++) {
				players[j].getStats().takeDamage(1000000);
			}
		}
		try {
			game.checkForKill();
			Thread.sleep(game.getRespawnTimer());
			game.respawn();
			for (int i = 0; i < teams.length; i++) {
				Player[] players = teams[i].getPlayers();
				for (int j = 0; j < players.length; j++) {
					assertEquals(false, players[j].isDied());
					
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
