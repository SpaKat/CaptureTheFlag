package Server;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import Message.GameInfo;

public class GameServerClient extends Thread{

	private Socket socket;
	private GameManager gameManager;
	private ServerObjInStreamThread in;
	public GameServerClient(Socket socket,GameManager gameManager) {
		this.setName("GameServerClient");
		this.socket = socket;
		this.gameManager = gameManager;
		this.start();
	}


	@Override
	public void run() {
		try {		
			in = new ServerObjInStreamThread(socket,gameManager);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			while (!this.isInterrupted()) {
				try {
					out.writeObject(gameManager.sendInfo());
					out.flush();
					out.writeObject(in.getPlayer());
					out.reset();
					System.out.println(System.currentTimeMillis());
					Thread.sleep(1);
				}catch (EOFException e) {
					System.err.println("Oops");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();		
		}
		System.out.println("Died");
	}


	public void kill() {
		try {
			socket.close();
		
		} catch (IOException e) {
			System.err.println("Die");
		}
		this.interrupt();
		in.interrupt();
	}

}
