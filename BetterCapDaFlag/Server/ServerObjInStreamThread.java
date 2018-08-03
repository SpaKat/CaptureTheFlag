package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Team;
import Message.GameTeamSelect;

public class ServerObjInStreamThread extends Thread{

	private ObjectInputStream in;
	private GameManager gameManager;
	private Player player;
	
	public ServerObjInStreamThread(Socket socket, GameManager gameManager) throws IOException {
		in = new ObjectInputStream(socket.getInputStream());
		this.gameManager = gameManager;
		this.start();
	}
	
	
	@Override
	public void run() {
		while (!this.isInterrupted()) {
			
			try {
				Object o = in.readObject();
			
				switch (o.getClass().getSimpleName()) {
				case "GameTeamSelect":
					//TODO
					GameTeamSelect gameTeamSelect = (GameTeamSelect) o;
					Team team = gameManager.getTeam(gameTeamSelect.getTeamId());
					player = new Player(gameTeamSelect.getStats(), team.getColor());
					team.addPlayer(player);
					break;
				case "Player":
					Player inputPlayer = (Player) o;
					player.setHeading(inputPlayer.getHeading());
					break;
				default:
					break;
				}
			} catch (Exception e) {	
				e.printStackTrace();
			}
		}
	}
	public Player getPlayer() {
		return player;
	}
}
