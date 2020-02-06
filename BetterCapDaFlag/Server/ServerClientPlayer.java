package Server;

import java.io.IOException;
import java.net.Socket;

import CaptureTheFlagGame.GameManager;

public class ServerClientPlayer {

	
	private Socket socket;
	private GameManager gm;
	private String id;
	
	public ServerClientPlayer(Socket s, GameManager gm, String id) {
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
		boolean delete = true;
		
		return delete;
	}

}
