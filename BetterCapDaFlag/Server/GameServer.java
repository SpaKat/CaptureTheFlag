package Server;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;

public class GameServer extends Thread{

	private ServerSocket serverSocket ;
	private int port = 8008;
	private ArrayList<GameServerClient> serverClients;
	private GameManager gameManager;
	
	public GameServer(GameManager gameManager) {
		this.setName("GameServer");
		this.gameManager = gameManager;
		serverClients  = new ArrayList<GameServerClient>();
		this.start();
	}

	@Override
	public void run() {
		try {
			serverSocket = new  ServerSocket(port);
			while (!this.isInterrupted()) {				
				Socket socket = serverSocket.accept();
				serverClients.add(new GameServerClient(socket,gameManager));
			}
			for (int i = 0; i < serverClients.size(); i++) {
				GameServerClient client = serverClients.get(i);
				client.interrupt();
			}
			serverSocket.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public String getIp () {
		String ip = "Not Found"; 
		try {
		 ip = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			//e.printStackTrace();
		}
		return ip;
	}
	
	public void kill() {
		try {
			serverSocket.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		this.interrupt();
	}
}
