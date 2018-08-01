package CaptureTheFlagGame;

import java.util.ArrayList;
import java.util.Random;

public class Team {

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
}
