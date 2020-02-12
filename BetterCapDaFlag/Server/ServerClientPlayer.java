package Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;

public class ServerClientPlayer {

	
	private Socket socket;
	private GameManager gm;
	private String id;
	private Player p;
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
		boolean delete = false;
		try {
			socket.setSoTimeout(10);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ois.readObject();
			//System.out.println();
		}catch(EOFException e) {
			delete = true;
			p.setConnected(false);
		}catch (Exception e) {
		}
		return delete;
	}

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
