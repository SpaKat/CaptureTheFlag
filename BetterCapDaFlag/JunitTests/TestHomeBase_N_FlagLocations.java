package JunitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CaptureTheFlagGame.Game;
import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Gameboard;
import CaptureTheFlagGame.Team;

class TestHomeBase_N_FlagLocations {


	private Gameboard gameboard;
	private Game game;
	private GameManager gm;
	int size = 1000;

	@BeforeEach
	void setUp()  {
		gameboard = new Gameboard(size, size);
		game = new Game(gameboard,4,20,3000);
		gm = new GameManager(gameboard, game);

	}

	@Test
	void homebaseLocations() {
		Team[] teams = game.getTeams();

		assertEquals(size-size, teams[0].getHomeBase().getX());
		assertEquals(size-size, teams[0].getHomeBase().getY());

		assertEquals(size, teams[1].getHomeBase().getX());
		assertEquals(size, teams[1].getHomeBase().getY());

		assertEquals(size, teams[2].getHomeBase().getX());
		assertEquals(size-size, teams[2].getHomeBase().getY());

		assertEquals(size-size, teams[3].getHomeBase().getX());
		assertEquals(size, teams[3].getHomeBase().getY());
	}
	
	@Test
	void flagLocations() {
		game.relocateFlags();
		Team[] teams = game.getTeams();

		assertEquals(size-size + teams[0].getHomeBase().getRadius() , teams[0].getFlag().getX());
		assertEquals(size-size + teams[0].getHomeBase().getRadius() , teams[0].getFlag().getY());

		assertEquals(size - teams[1].getHomeBase().getRadius() , teams[1].getFlag().getX());
		assertEquals(size - teams[1].getHomeBase().getRadius() , teams[1].getFlag().getY());

		assertEquals(size - teams[2].getHomeBase().getRadius() , teams[2].getFlag().getX());
		assertEquals(size-size + teams[2].getHomeBase().getRadius() , teams[2].getFlag().getY());

		assertEquals(size-size + teams[3].getHomeBase().getRadius() , teams[3].getFlag().getX());
		assertEquals(size - teams[3].getHomeBase().getRadius() , teams[3].getFlag().getY());
	}
}
