package Client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

import CaptureTheFlagGame.Game;
import CaptureTheFlagGame.Statistics;
import Server.Message.Action;
import Server.Message.CurrentGameState;
import Server.Message.RequestCurrentGameState;
import Server.Message.SetupConnection;
import Server.Message.setupPlayer;

public class ClientAPI implements Runnable{

	private Socket socket;
	private DatagramSocket datasocket;
	private long id;
	private int teamsInGame;
	private double heading;
	private Game game;
	private boolean running = true;
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
	public void sendRequestForCurrentGame() throws IOException {
		sendMessage(new RequestCurrentGameState(id));
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
		running  = false;
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
	public void setupPlayer(Statistics statistics, int team,String name) throws IOException {
		sendMessage(new setupPlayer(id,statistics ,team,name));
	}
	public int getTeamsInGame() {
		return teamsInGame;
	}
	@Override
	public void run() {
		while(running ) {
			byte[] b = new byte[10000];
			DatagramPacket dp = new DatagramPacket(b, b.length);

			try {
				datasocket.setSoTimeout(10);
				datasocket.receive(dp);
				process(dp);
				if(checkConnection()) {
					close();
				}
			} catch (Exception e) {
				//	e.printStackTrace();
			}
		}
	}
	private void process(DatagramPacket dp) throws IOException, ClassNotFoundException {
		ByteArrayInputStream inobject = new ByteArrayInputStream(dp.getData());
		ObjectInputStream findobject = new ObjectInputStream(inobject);
		Object o = findobject.readObject();
		//System.out.println(o.getClass().getSimpleName());
		switch (o.getClass().getSimpleName()) {
		case "CurrentGameState":
			CurrentGameState cgs = (CurrentGameState) o;
			processCurrentGameState(cgs);
			break;
		
		default:

			break;
		}
	}
	private void processCurrentGameState(CurrentGameState cgs) {
		game = cgs.getGame();
	}
	
	public Game getGame() {
		return game;
	}
	
}

