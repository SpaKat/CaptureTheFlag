package Client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameClient extends Thread {

	private String ip;
	
	public GameClient(String ip) {
		this.ip = ip;
		this.start();
	}
	@Override
	public void run() {
		try {
			Socket s = new Socket(ip,8008);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());

			s.close();
		} catch (Exception e) {

		}
	}

	
	public static void main(String[] args) throws Exception{
		new GameClient("127.0.0.1");
	}

}
