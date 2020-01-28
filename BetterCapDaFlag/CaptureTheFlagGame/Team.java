package CaptureTheFlagGame;

import java.io.Serializable;
import java.util.Random;

public class Team implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -986900377830035185L;
	private Flag flag;
	private HomeBase homeBase;
	private Player players[];
	private int color; //hex
	private int id;
	private int score;

	public Team(int id) {
		this.id = id;
		Random rn = new Random();
		color = rn.nextInt(0xFFFFFF+1) ;
		flag = new Flag(color);
		homeBase = new HomeBase(color);
		score = 0;
	}

	public void setMaxPlayers(int maxPlayers) {
		players = new Player[maxPlayers];
	}
	public void setScore(int score) {
		this.score = score;
	}
	public HomeBase getHomeBase() {
		return homeBase;
	}
	public int getId() {
		return id;
	}
	public Flag getFlag() {
		return flag;
	}
	public int getColor() {
		return color;
	}
	public int getScore() {
		return score;
	}

	public boolean full() {
		boolean b = true;
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
				b = false;
			}
		}
		return b;
	}

	public boolean addPlayer(Player player) {
		boolean b;
		if(full()) {
			//	System.out.println("Team is full");
			b = false;
		}else {
			boolean added = false;
			for (int i = 0; i < players.length; i++) {
				if (!added && players[i] == null) {
					players[i] = player;
					player.setColor(color);
					added = true;
				}
			}
			//	System.out.println(Arrays.toString(players));
			b = true;
		}
		return b;
	}
	public Player[] getPlayers() {
		return players;
	}

	public void spawnPlayers(Gameboard gameboard) {
		for (int i = 0; i < players.length; i++) {
			if(!players[i].isSpawned()) {
				double rnd = Math.random()*homeBase.getRadius();
				gameboard.spawnGameColorObject(players[i], id, rnd);
			}
		}
	}

	public void checkForDiedPlayers() {

		for (int i = 0; i < players.length; i++) {
			if(players[i].isDied() && ! players[i].isRespawning()) {
				players[i].setRespawning(true);
				players[i].diedAt();
			}
		}
	}

	public void respawnPlayers(Gameboard gameboard, long respawnTimer) {
		for (int i = 0; i < players.length; i++) {
			if (players[i].isRespawning() && players[i].readyToRespawn(respawnTimer)) {
				players[i].setRespawning(false);
				players[i].fullHealth();
				gameboard.spawnGameColorObject(players[i], id, Math.random()*homeBase.getRadius());
			}
		}
	}

	public void checkBullets(Team team) {
		Player[] enemys = team.getPlayers();
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < enemys.length; j++) {
				Bullet[] enemybullets = enemys[j].getBullets();
				for (int k = 0; k < enemybullets.length; k++) {
					if (enemybullets[k] != null) {
						if (Calculations.distance(players[i], enemybullets[k]) <= Calculations.combineRadius(players[i], enemybullets[k])) {
							players[i].getStats().takeDamage(enemybullets[k].getDamage());
							enemybullets[k].setDied(true);
						}
					}
				}
			}
		}
	}

	public void moveFlag() {
		flag.move();
	}

	public void movePlayers() {
		for (int i = 0; i < players.length; i++) {
			players[i].move();
		}
	}

	public void moveBullets() {
		for (int i = 0; i < players.length; i++) {
			players[i].bullets();
		}
	}

	public void cleanDiedBullets() {
		for (int i = 0; i < players.length; i++) {
			players[i].cleanDiedBullets();
		}
	}

	public void scored() {
		score++;
	}
}
