package Gui;

import java.util.ArrayList;

import javafx.application.Platform;

public class GameGUIRunnable implements Runnable {

	private ArrayList<GameGUITeam> guiTeams;
	private boolean running = true;
	private long updateInterval = 16;

	public GameGUIRunnable(	 ArrayList<GameGUITeam> guiTeams) {
		this.guiTeams = guiTeams;
	}

	@Override
	public void run() {
		while (running) {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					guiTeams.forEach(team -> {
						team.relocate();
						team.updatePlayers();
						
					});
				}
			});
			try {
				Thread.sleep(updateInterval);
			} catch (Exception e) {
				//System.err.println("GAMEGUIRUNNABLE is awake");
			}
		}
	}
	
	public void close() {
		running = false;
	}


	
}
