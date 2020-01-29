package Gui;

import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import CaptureTheFlagGame.Team;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameGUI extends Pane {

	private ArrayList<GameGUITeam> guiTeams;
	private GameManager gm;
	
	public GameGUI(GameManager gm) {
		this.gm = gm;
		gm.relocatePieces();
		//TODO
		tester();
		
		setStyle("-fx-background-color: black");
		guiTeams = new ArrayList<>();
		Team teams[] = gm.getTeams();
		for (int i = 0; i < teams.length; i++) {
			guiTeams.add(new GameGUITeam(teams[i], this));
		}
		
		widthProperty().addListener(  (obs,old,newV ) -> {
			gm.setwidth(newV.doubleValue());
			gm.relocatePieces();
			guiTeams.forEach(team->team.relocate());
		});
		heightProperty().addListener(  (obs,old,newV ) -> {
			gm.setheight(newV.doubleValue());
			gm.relocatePieces();
			guiTeams.forEach(team->team.relocate());
		});
	}

	private void tester() {
		// TODO Auto-generated method stub
		
		
		Stage stage = new Stage();
		VBox vb = new VBox();
		vb.getChildren().add(testgmtaketurn());
		vb.getChildren().add(testspawnplayer());
		Scene scene = new Scene(new ScrollPane(vb) );
		stage.setScene(scene);
		stage.show();
		
	}

	private Button testgmtaketurn() {
		Button takeoneturn = new Button("Take one Game manager turn");
		
		takeoneturn.setOnAction(e->{
			gm.OneTurn();
		});
		
		return takeoneturn ;
	}

	private Button testspawnplayer() {
		Button spawnplayer = new Button("Spawn Player on team 0");
		spawnplayer.setOnAction(e->{
			gm.addPlayer(new Player(new Statistics(1, 1, 1, 1)));	
		});
		return spawnplayer ;
	}
	
}
