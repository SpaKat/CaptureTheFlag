package Server;

import java.net.ServerSocket;
import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.GameRunnable;
import Gui.GameGUIRunnable;
import Gui.GameGUITeam;
import html.HTMLServer;

public class ServerGameRunnable implements Runnable {



	private GameRunnable backgroundUpdate;
	private GameGUIRunnable guiUpdate;
	private boolean running = true;
	private ServerSocket ss;
	private HTMLServer htmlserver;
	public ServerGameRunnable(GameManager gm,ArrayList<GameGUITeam> guiTeams) {
		backgroundUpdate = new GameRunnable(gm);
		guiUpdate = new GameGUIRunnable(guiTeams);
		htmlserver = new HTMLServer(8008,gm);
	}
	
	@Override
	public void run() {
		new Thread(backgroundUpdate).start();
		new Thread(guiUpdate).start();
		/*try {
			 ss = new ServerSocket(8008);
			while (running) {
				try {
					Socket s = ss.accept();
					Thread.sleep(1);
				} catch (Exception e) {
				//	System.err.println("GameRunnable woke up");
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
 		
	}

	public void close() {
		running  = false;
		try {
			backgroundUpdate.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println("failed to close background update");
		}
		try {
			guiUpdate.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println("failed to close gui update");
		}
		try {
			ss.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println("failed to close server Socket");
		}
		htmlserver.close();
	}
}
