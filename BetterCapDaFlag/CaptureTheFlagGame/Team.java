package CaptureTheFlagGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Team implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -986900377830035185L;
	private Flag flag;
	private HomeBase homeBase;
	private ArrayList<Player> players;
	private int color; //hex
	private int maxPlayers;
	private int id;
	
	public Team(int id) {
		this.id = id;
		players = new ArrayList<>();
		Random rn = new Random();
		color = rn.nextInt(0xFFFFFF+1) ;
		flag = new Flag(color);
		homeBase = new HomeBase(color);
		maxPlayers = 20;
	}

	public void setMaxPlayers(int parseInt) {
		maxPlayers = parseInt;
	}
	public int getMaxPlayers() {
		return maxPlayers;
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
		boolean b = false;
		if (players.size() >= maxPlayers) {
			b = true;
		}
		return b;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void spawnPlayers() {
		for (int i = 0; i < players.size(); i++) {
			if(!players.get(i).isSpawned()) {
				players.get(i).setX(homeBase.getX());
				players.get(i).setY(homeBase.getY());
			}
		}
	}
}
