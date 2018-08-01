package Server;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer extends Thread{

	private ServerSocket serverSocket ;
	private int port = 8008;
	private ThreadGroup serverClients;
	
	public GameServer() {
		serverClients  = new ThreadGroup("ServerClients");
		this.start();
	}

	@Override
	public void run() {
		try {
			serverSocket = new  ServerSocket(port);
		//	while (!this.isInterrupted()) {
				Socket socket = serverSocket.accept();
								System.out.println("YES");
				
		//	}
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
