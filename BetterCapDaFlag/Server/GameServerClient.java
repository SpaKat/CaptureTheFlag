package Server;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;

public class GameServerClient extends Thread{

	private Socket socket;
	private GameManager gameManager;
	
	public GameServerClient(Socket socket,GameManager gameManager) {
		this.setName("GameServerClient");
		this.socket = socket;
		this.gameManager = gameManager;
		this.start();
	}



	@Override
	public void run() {
		try {


			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			while (!this.isInterrupted()) {
				try {

					out.writeObject(gameManager.sendInfo());
					out.reset();

					Player message = (Player) in.readObject();
					

					
					
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
			this.interrupt();
		} catch (IOException e) {
			System.err.println("Die");
		}
	}

}
