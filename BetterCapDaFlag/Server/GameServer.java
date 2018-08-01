package Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer extends Thread{

	private ServerSocket serverSocket ;
	private int port = 8008;
	private ArrayList<GameServerClient> serverClients;

	public GameServer() {
		serverClients  = new ArrayList<GameServerClient>();
		this.start();
	}

	@Override
	public void run() {
		try {
			serverSocket = new  ServerSocket(port);
			while (!this.isInterrupted()) {
				Socket socket = serverSocket.accept();
				serverClients.add(new GameServerClient(socket));
			//	System.out.println("YES");
			}
			for (int i = 0; i < serverClients.size(); i++) {
				GameServerClient client = serverClients.get(i);
				client.interrupt();
			}
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	public void kill() {
		try {
			serverSocket.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
