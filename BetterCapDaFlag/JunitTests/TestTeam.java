package JunitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CaptureTheFlagGame.Game;
import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Gameboard;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import CaptureTheFlagGame.Team;

public class TestTeam {

	private Gameboard gameboard;
	private Game game;
	@Before
	public void setUp(){
		gameboard = new Gameboard(1000, 1000);
		game = new Game(gameboard,4,20,3000);

	}


	@Test
	public void addPlayersToFull() {
		Team t = game.getTeam(0);
		int count = 0;
		for (int i = 0; i < t.getPlayers().length; i++) {
			boolean added = t.addPlayer( new Player(new Statistics(1, 1, 1, 1)) );
			if (added) {
				count++;
			}
		}
		assertEquals(20,count);
	}
	@Test
	public void addPlayersToFullWithHalfTeam() {
		Team t = game.getTeam(0);
		int count = 0;
		for (int j = 0; j < 20; j = j+2) {
			t.getPlayers()[j] = new Player(new Statistics(1, 1, 1, 1));
		}
		for (int i = 0; i < t.getPlayers().length; i++) {
			boolean added = t.addPlayer( new Player(new Statistics(1, 1, 1, 1)) );
			if (added) {
				count++;
			}
		}
		assertEquals(10,count);
	}
	@Test
	public void maxPlayers() {
		Team t = game.getTeam(0);
		t.setMaxPlayers(57);
		assertEquals(57,t.getPlayers().length);
	}
}
