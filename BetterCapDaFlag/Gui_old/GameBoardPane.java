package Gui_old;


import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Team;
import javafx.scene.layout.Pane;

public class GameBoardPane extends Pane {

	private GameManager gameManager;
	private ArrayList<GameGUIteams> teamGUI;

	public GameBoardPane(GameManager gameManager) {
		this.gameManager = gameManager;
		teamGUI = new ArrayList<>();
		setStyle("-fx-background-color: black; "
				+ "-fx-border-color: green");

		heightProperty().addListener( (obs,oldV,newHieght ) ->{
			gameManager.setheight(newHieght.doubleValue());
		});
		widthProperty().addListener( (obs,oldV,newWidth ) ->{
			gameManager.setwidth(newWidth.doubleValue());
		});

	}



	public void start() {
		for (int i = 0; i < gameManager.getTeams(); i++) {
			Team team = gameManager.getTeam(i);
			teamGUI.add(new GameGUIteams(team,this));
		}
	}
	public void relocatePieces() {
		for (int i = 0; i < teamGUI.size(); i++) {
			teamGUI.get(i).relocate();
		}
	}

	public void addPlayers() {
		for (int i = 0; i < gameManager.getTeams(); i++) {
			teamGUI.forEach(add ->{
				add.addPlayers();
			});
		}
	}


	public void clean() {
		//TODO

	}





}
