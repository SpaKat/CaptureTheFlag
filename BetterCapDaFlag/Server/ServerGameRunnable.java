package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;

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
	private ServerClientRunnable serverclient;
	private int port = 8008;

	public ServerGameRunnable(GameManager gm,ArrayList<GameGUITeam> guiTeams) throws SocketException {
		backgroundUpdate = new GameRunnable(gm);
		guiUpdate = new GameGUIRunnable(guiTeams);
		htmlserver = new HTMLServer(port+2,gm);
		serverclient = new ServerClientRunnable(gm,port+1);
	}

	@Override
	public void run() {
		new Thread(backgroundUpdate).start();
		new Thread(guiUpdate).start();
		try {
			ss = new ServerSocket(port);
			while (running) {
				try {
					Socket s = ss.accept();
					serverclient.newClient(s);
				} catch (Exception e) {
					//	System.err.println("GameRunnable woke up");
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


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
		serverclient.close();
	}

}
