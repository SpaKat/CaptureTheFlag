package Gui;

import CaptureTheFlagGame.Game;
import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Gameboard;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameMainPane extends BorderPane {
	
	private GameManager gm;
	private GameGUI ggui;
	public GameMainPane() {
		double startX = 500;
		double startY = 500;
		setPrefHeight(startY);
		setPrefWidth(startX);
		showGMSettings();
	}

	
	private void showGMSettings() {
		HBox numberofPlayershb = new HBox();
		TextField numberofPlayer = numberOfPLayers(numberofPlayershb);
		HBox numberofTeamshb = new HBox(30);
		TextField numberofTeam = numberofTeams(numberofTeamshb);
		HBox respawnhb = new HBox(40);
		TextField numberofrespawn = respawnTime(respawnhb);
		Button save = new Button("Enter");
		save.setOnAction(s ->{
			try {
				Gameboard gameBoard = new Gameboard(getPrefWidth(), getPrefHeight());
				Game game = new Game(gameBoard, 
						Integer.parseInt(numberofTeam.getText()), 
						Integer.parseInt(numberofPlayer.getText()), 
						Integer.parseInt(numberofrespawn.getText()));
				gm = new GameManager(gameBoard,game);
				launchGame();
			}catch (Exception e) {
				// TODO
				e.printStackTrace();
			}
		});
		VBox backVbox = new VBox();
		backVbox.setAlignment(Pos.CENTER);
		Label title = new Label("Enter Game Settings");
		backVbox.getChildren().addAll(title,numberofPlayershb,numberofTeamshb,respawnhb,save);
		setCenter(backVbox);
	}
	private TextField respawnTime(HBox respawnhb) {
		respawnhb.setAlignment(Pos.CENTER);
		Text askForrespawn = new Text("Enter Respawn Timer in milliseconds");
		TextField numberofrespawn = new TextField("3000");
		numberofrespawn.textProperty().addListener( (obs ,old,newV ) ->{
			try {
				int i = Integer.parseInt(newV);
				if (i < 01 ) {
					numberofrespawn.setText("3000");
				}

			}catch (Exception e) {
				numberofrespawn.setText("3000");
			}
		});
		respawnhb.getChildren().addAll(askForrespawn,numberofrespawn);
		return numberofrespawn;
	}

	private TextField numberofTeams(HBox numberofTeamshb) {
		numberofTeamshb.setAlignment(Pos.CENTER);
		Text askForNumTeams = new Text("Enter Number of Teams (limit 2-4)");
		TextField numberofTeam = new TextField("4");
		numberofTeam.textProperty().addListener( (obs ,old,newV ) ->{
			try {
				int i = Integer.parseInt(newV);
				if (i < 2 || i > 4) {
					numberofTeam.setText("2");
				}

			}catch (Exception e) {
				numberofTeam.setText("2");
			}
		});
		numberofTeamshb.getChildren().addAll(askForNumTeams,numberofTeam);
		return numberofTeam;
	}

	private TextField numberOfPLayers(HBox numberofPlayershb) {
		numberofPlayershb.setAlignment(Pos.CENTER);
		Text askForNumPlayers = new Text("Enter Number of Players per Team (ints)");
		TextField numberofPlayer = new TextField("20");
		numberofPlayer.textProperty().addListener( (obs ,old,newV ) ->{
			try {
				Integer.parseInt(newV);
			}catch (Exception e) {
				numberofPlayer.setText("20");
			}
		});
		numberofPlayershb.getChildren().addAll(askForNumPlayers,numberofPlayer);
		return numberofPlayer;
	}
	
	private void launchGame() {
		// TODO
		// make base game
		ggui = new GameGUI(gm);
		setCenter(ggui);
	}
	public void close() {
		// TODO final clean up
		try {
			ggui.close();
		}catch (Exception e) {
			System.err.println("Failed to close GameGUI");
		}
		
	}
	

}
