package CaptureTheFlagGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

	public Team(int id) {
		this.id = id;
		Random rn = new Random();
		color = rn.nextInt(0xFFFFFF+1) ;
		flag = new Flag(color);
		homeBase = new HomeBase(color);
	}

	public void setMaxPlayers(int maxPlayers) {
		players = new Player[maxPlayers];
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

	public void spawnPlayers() {
		for (int i = 0; i < players.length; i++) {
			if(!players[i].isSpawned()) {
				players[i].setX(homeBase.getX());
				players[i].setY(homeBase.getY());
			}
		}
	}
}
