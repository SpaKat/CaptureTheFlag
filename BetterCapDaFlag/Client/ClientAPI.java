package Client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Server.Message.Heading;
import Server.Message.SetupConnection;
import Server.Message.setupPlayer;

public class ClientAPI {

	private Socket socket;
	private DatagramSocket datasocket;
	private long id;
	private int teamsInGame;
	private Player player;
	public ClientAPI(String ip, int port) throws Exception{

		socket = new Socket(ip,port);
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		SetupConnection sc = (SetupConnection) ois.readObject();
		id = sc.getId();
		teamsInGame = sc.getTeams();
		datasocket = new DatagramSocket();
		datasocket.connect(socket.getInetAddress(), port+1);

	}
	public void sendMessage(Object message) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(message);
		oos.flush();
		DatagramPacket dp = new DatagramPacket(os.toByteArray(), os.toByteArray().length);
		datasocket.send(dp);
	}
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datasocket.close();
	}
	public static void main(String[] args) {

		for (int i = 0; i < 2; i++) {



			Runnable r = new Runnable() {

				@Override
				public void run() {
					try {
						ClientAPI test = new ClientAPI("127.0.0.1", 8008);
						test.sendMessage(new setupPlayer(test.id, new Statistics(20, 20, 20, 20) , 0));
						double heading =0;
						for (int i = 0; i < 1000; i++) {
							test.sendMessage(new Heading(test.id, heading));
							heading += Math.PI/36;
							Thread.sleep(1);
						}
						Thread.sleep(100);
					//	test.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			new Thread(r).start();
		}

	}

}


