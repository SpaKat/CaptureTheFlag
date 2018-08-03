package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ClientObjInStreamThread extends Thread {

	private ObjectInputStream in;
	
	public ClientObjInStreamThread(InputStream inputStream) throws IOException {
		in = new ObjectInputStream(inputStream);
	}

	@Override
	public void run() {

		try {
			while(!this.isInterrupted()) {
				
				
				Thread.sleep(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
