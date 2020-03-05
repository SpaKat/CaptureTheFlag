package Server;

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

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Server.Message.Action;
import Server.Message.CurrentGameState;
import Server.Message.RequestCurrentGameState;
import Server.Message.setupPlayer;

public class ServerClientPlayer implements Runnable{


	private Socket socket;
	private GameManager gm;
	private long id;
	private Player player;
	private DatagramSocket datasocket;
	private boolean running = true;
	public ServerClientPlayer(Socket s, GameManager gm, long id,DatagramSocket ds) {
		this.socket = s;
		this.gm = gm;
		this.id = id;
		this.datasocket = ds;
		new Thread(this).start();
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
			} catch (Exception e) {
				//	e.printStackTrace();
			}
		}
	}
	public void close() {
		running = false;
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//.printStackTrace();
		}
		try {
			datasocket.close();
		}catch (Exception e) {
			// TODO: handle exception
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
		if(player != null) {
			player.setConnected(false);
		}
		running = false;
		close();
		return delete;
	}

	public Player getPlayer() {
		return player;
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
				player.setName(sp.getName());
				boolean playerisadded = gm.addPlayer(player, teamId);
				if (!playerisadded) {
					disconnected();
				}
			}
		}
	}

	public void processAction(Action a) {
		if(player!=null) {
			player.setHeading(a.getHeading());
			if (a.isFire()) {
				player.fireBullet();
			}
		}
	}


	private void process(DatagramPacket dp) throws Exception {
		ByteArrayInputStream inobject = new ByteArrayInputStream(dp.getData());
		ObjectInputStream findobject = new ObjectInputStream(inobject);
		Object o = findobject.readObject();
		//System.out.println(o.getClass().getSimpleName());
		switch (o.getClass().getSimpleName()) {
		case "setupPlayer":
			setupPlayer sp = (setupPlayer) o;
			processSetupPlayer(sp);

			break;
		case "Action":
			Action a = (Action) o;
			processAction(a);

			break;
		case "RequestCurrentGameState":
			RequestCurrentGameState CGS = (RequestCurrentGameState) o;
			processRequestCurrentGameState(CGS);
			break;
		default:

			break;
		}

	}
	private void processRequestCurrentGameState(RequestCurrentGameState request) {
		CurrentGameState cgs = new CurrentGameState(id,gm.getGame());
		try {
			sendMessage(cgs);
		} catch (IOException e) {
			
		}
	}
	public void sendMessage(Object message) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(message);
		oos.flush();
		DatagramPacket dp = new DatagramPacket(os.toByteArray(), os.toByteArray().length);
		datasocket.send(dp);
	}
}
