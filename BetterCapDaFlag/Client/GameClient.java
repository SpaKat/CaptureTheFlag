package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Message.GameInfo;
import Message.GameTeamSelect;

public class GameClient extends Thread {

	private String ip;
	private int port = 8008;
	private GameInfo gameInfo;
	private ObjectOutputStream out;
	private Statistics stats;
	private Player player;
	public GameClient(String ip,Statistics stats) {
		this.setName("GameClient");
		this.stats = stats;
		this.ip = ip;
		this.start();
	}
	@Override
	public void run() {
		try {
			Socket s = new Socket(ip,port);
			out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());

			while(!this.isInterrupted()) {
				System.out.println("Running");
				Object o = in.readObject();
			//	System.out.println(o.getClass().getSimpleName());
				switch (o.getClass().getSimpleName()) {
				case "GameInfo":
					gameInfo = (GameInfo) o;
				//	System.out.println(gameInfo);
					break;
				case "Player":
					player = (Player) o;
					System.out.println(player.isSpawned());
					break;
				default:	
					break;
				}
				
				Thread.sleep(1);
			}
			s.close();
		} catch (Exception e) {

		}
	}
	public GameInfo getGameInfo() {
		return gameInfo;
	}
	public void selectTeam(int teamId) {
		try {
			out.writeObject(new GameTeamSelect(teamId,stats));
			out.reset();
			System.out.println("Wrote To Select Team");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
