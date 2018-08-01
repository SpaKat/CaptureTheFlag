package Server;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Communication.Message;
import Communication.PlayerMessage;
import javafx.scene.shape.Circle;

public class GameServerClient extends Thread{

	private Socket socket;

	public GameServerClient(Socket socket) {
		this.setName("GameServerClient");
		this.socket = socket;
		this.start();
	}



	@Override
	public void run() {
		try {


			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			while (!this.isInterrupted()) {
				try {



					Message message = (Message) in.readObject();
					int id = message.getID();

					
					
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
