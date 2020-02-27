package Server;

import java.io.ObjectOutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;
import Server.Message.SetupConnection;

public class ServerClientRunnable implements Runnable{


	private GameManager gm;
	private DatagramSocket datasocket;
	private boolean running = true;
	private ArrayList<ServerClientPlayer> cilentPlayers;
	private long Id = 0;
	public ServerClientRunnable(GameManager gm, int port) throws SocketException {
		this.gm = gm;
		datasocket = new DatagramSocket(port);	
		datasocket.setSoTimeout(1);
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


			ServerClientPlayer[] scp = new ServerClientPlayer[cilentPlayers.size()];
			cilentPlayers.toArray(scp);
			for (int i = 0; i < scp.length; i++) {
				if(scp[i].checkConnection()) {
					cilentPlayers.remove(scp[i]);
				}
			}
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
			}
			//	System.out.println(cilentPlayers.size());

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
		if(cilentPlayers.size() <= gm.getTeams().length*gm.getTeams()[0].getPlayers().length) {
			DatagramSocket ds = new DatagramSocket();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(new SetupConnection(Id,gm.getTeams().length,ds.getInetAddress(),ds.getLocalPort()));
			oos.flush();
			ServerClientPlayer scp = new ServerClientPlayer(s,gm,Id,ds);
			cilentPlayers.add(scp);
			Id++;
		}
		//System.out.println(s.getInetAddress() + "       " +ds.getLocalPort());
	}
}
