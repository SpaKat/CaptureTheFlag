package Client;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Server.Message.Action;
import Server.Message.SetupConnection;
import Server.Message.setupPlayer;

public class ClientAPI {

	private Socket socket;
	private DatagramSocket datasocket;
	private long id;
	private int teamsInGame;
	private double heading;
	
	public ClientAPI(String ip, int port) throws Exception{

		socket = new Socket(ip,port);
		socket.setSoTimeout(10);
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		SetupConnection sc = (SetupConnection) ois.readObject();
		setId(sc.getId());
		teamsInGame = sc.getTeams();
		datasocket = new DatagramSocket();
		datasocket.connect(socket.getInetAddress(), sc.getPort());
		//System.out.println(datasocket.getLocalAddress());

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
	public void sendAction(double heading, boolean fire) throws IOException {
		this.heading = heading;
		sendMessage(new Action(id, heading, fire));
	}
	public void checkForConnection() {
		if(checkConnection()) {
			try {
				datasocket.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	private boolean checkConnection() {
		//true delete
		boolean delete = false;
		try {
			socket.setSoTimeout(1);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ois.readObject();
			//System.out.println();

		}catch(EOFException e) {
			delete = disconnected();
			//e.printStackTrace();
		}catch (SocketException e) {
			delete = disconnected();
			//e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return delete;
	}
	private boolean disconnected() {
		boolean	delete = true;
		close();
		return delete;
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void sendFire() throws IOException {
		sendAction(heading, true);
	}
	public void setupPlayer(Statistics statistics, int i) throws IOException {
		sendMessage(new setupPlayer(id,statistics ,i));
	}
	

}

