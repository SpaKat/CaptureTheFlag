package Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;

public class ServerClientRunnable implements Runnable{


	private GameManager gm;
	private DatagramSocket datasocket;
	private boolean running = true;
	private ArrayList<ServerClientPlayer> cilentPlayers;

	public ServerClientRunnable(GameManager gm, int port) throws SocketException {
		this.gm = gm;
		datasocket = new DatagramSocket(port);	
		datasocket.setSoTimeout(10);
		cilentPlayers = new ArrayList<>();
		new Thread(this).start();
	}

	//	private void sentPort() throws IOException {
	//		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	//		InternetAddress ia = new InternetAddress(socket.getLocalAddress().getHostAddress(), socket.getPort());
	//	}

	@Override
	public void run() {

	

		while (running) {
			// test
			
			byte[] b = new byte[5000];
			DatagramPacket dp = new DatagramPacket(b, b.length);
			try {
				datasocket.receive(dp);
				process(dp);
			} catch (Exception e) {
			//	e.printStackTrace();
			}
			ServerClientPlayer[] scp = new ServerClientPlayer[cilentPlayers.size()];
			cilentPlayers.toArray(scp);
			for (int i = 0; i < scp.length; i++) {
				if(scp[i].checkConnection()) {
					cilentPlayers.remove(scp[i]);
				}
			}
		//	System.out.println(cilentPlayers.size());
			
		}
	}
	private void process(DatagramPacket dp) throws Exception {
		ByteArrayInputStream inobject = new ByteArrayInputStream(dp.getData());
		ObjectInputStream findobject = new ObjectInputStream(inobject);
		Object o = findobject.readObject();
		System.out.println(o.getClass().getSimpleName());
		switch (o.getClass().getSimpleName()) {
		case "":
			
			break;

		default:
			
			break;
		}
	
	}

	public void close() {
		running   = false;
		datasocket.close();
		cilentPlayers.forEach(sp->{
				sp.close();
		});

	}

	public void newClient(Socket s, String id) {
		ServerClientPlayer scp = new ServerClientPlayer(s,gm,id);
		cilentPlayers.add(scp);
	}
}
