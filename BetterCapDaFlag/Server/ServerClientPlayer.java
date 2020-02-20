package Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Server.Message.Heading;
import Server.Message.setupPlayer;

public class ServerClientPlayer {


	private Socket socket;
	private GameManager gm;
	private long id;
	private Player player;
	public ServerClientPlayer(Socket s, GameManager gm, long id) {
		this.socket = s;
		this.gm = gm;
		this.id = id;
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkConnection() {
		//true delete
		boolean delete = false;
		try {
			socket.setSoTimeout(10);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ois.readObject();
			//System.out.println();

		}catch(EOFException e) {
			delete = true;
			if(player != null) {
				player.setConnected(false);
			}
		}catch (Exception e) {
		} 
		return delete;
	}

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	public long getId() {
		return id;
	}

	public void processSetupPlayer(setupPlayer sp) {
		
		Statistics stats = sp.getStats();
		int teamId = sp.getTeamID();
		if(player ==null) {
			if(stats.getRateing()) {
				player = new Player(stats);
				gm.addPlayer(player, teamId);
			}
		}
	}

	public void processHeading(Heading h) {
		player.setHeading(h.getHeading());
	}
}
