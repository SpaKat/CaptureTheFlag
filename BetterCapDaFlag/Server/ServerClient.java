package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import CaptureTheFlagGame.GameManager;

public class ServerClient implements Runnable{

	
	private GameManager gm;
	private DatagramSocket ds;
	private boolean running = true;
	
	
	public ServerClient(GameManager gm, DatagramSocket ds) {
		this.gm = gm;
		this.ds = ds;
	}
	
	@Override
	public void run() {
		
	
		while (running) {
			// test
			byte[] b = new byte[5000];
			DatagramPacket dp = new DatagramPacket(b, b.length);
			
			try {
				System.out.println("waiting");
				ds.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(new String(dp.getData()));
		}
	}
	public void close() {
		running   = false;
		ds.close();
	}
}
