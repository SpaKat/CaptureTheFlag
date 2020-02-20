package Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;

import CaptureTheFlagGame.GameManager;
import Server.Message.Heading;
import Server.Message.SetupConnection;
import Server.Message.setupPlayer;

public class ServerClientRunnable implements Runnable{


	private GameManager gm;
	private DatagramSocket datasocket;
	private boolean running = true;
	private ArrayList<ServerClientPlayer> cilentPlayers;
	private long Id = 0;
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
	//	System.out.println(o.getClass().getSimpleName());
		switch (o.getClass().getSimpleName()) {
		case "setupPlayer":
			setupPlayer sp = (setupPlayer) o;
			cilentPlayers.forEach(cp->{
				if (sp.getId() == cp.getId()) {
					cp.processSetupPlayer(sp);
				}
			});
			break;
		case "Heading":
			Heading H = (Heading) o;
			cilentPlayers.forEach(cp->{
				if (H.getId() == cp.getId()) {
					cp.processHeading(H);
				}
			});
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

	public void newClient(Socket s) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(new SetupConnection(Id,gm.getTeams().length));
		oos.flush();
		ServerClientPlayer scp = new ServerClientPlayer(s,gm,Id);
		cilentPlayers.add(scp);
		Id++;
	}
}
