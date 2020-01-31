package Gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.GameRunnable;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import CaptureTheFlagGame.Team;
import Server.ServerGameRunnable;
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
	private GameRunnable backgroundUpdate;
	private GameGUIRunnable guiUpdate;
	private ServerGameRunnable server;
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
		vb.getChildren().add(testfull4Teams());
		vb.getChildren().add(testGameThreads());
		vb.getChildren().add(testremoverndplayer());
		vb.getChildren().add(testremoveALLplayer());
		vb.getChildren().add(testServerGame());
		Scene scene = new Scene(new ScrollPane(vb) );
		stage.setScene(scene);
		stage.show();

	}



	private Button testgmtaketurn() {
		Button takeoneturn = new Button("Take one Game manager turn");

		takeoneturn.setOnAction(e->{
			long t = System.currentTimeMillis();
			gm.OneTurn();
			System.out.println("turn took = " + (System.currentTimeMillis() - t)+" ms");
			guiTeams.forEach(team -> {
				team.updatePlayers();
				team.relocate();
			});
			//	System.out.println(getChildren().size());
			//	System.out.println("\n");
		});

		return takeoneturn ;
	}

	private Button testspawnplayer() {
		Button spawnplayer = new Button("Spawn Player on team 0");
		spawnplayer.setOnAction(e->{
			gm.addPlayer(new Player(new Statistics(1, 1, 1, 97)),0);	
		});
		return spawnplayer ;
	}
	private Button testfull4Teams() {
		Button spawnplayer = new Button("Spawn 4 full teams");
		spawnplayer.setOnAction(e->{
			for (int i = 0; i <4; i++) {
				for (int j = 0; j <20; j++) {
					gm.addPlayer(new Player(new Statistics(1, 1, 1, 50)),i);	
				}
			}

		});
		return spawnplayer ;
	}
	private Button testGameThreads() {
		Button gameThreads = new Button("run game threads");
		gameThreads.setOnAction(e->{
			backgroundUpdate = new GameRunnable(gm);
			guiUpdate = new GameGUIRunnable(guiTeams);
			Thread t = new Thread(backgroundUpdate);
			Thread t2 = new Thread(guiUpdate);
			t.start();
			t2.start();
		});
		return gameThreads;
	}
	private Button testremoverndplayer() {
		Button spawnplayer = new Button("Remove random player");
		spawnplayer.setOnAction(e->{
			Random rn = new Random();
			gm.getTeams()[rn.nextInt(4)].getPlayers()[rn.nextInt(20)] = null;	
			for (int i = 0; i < gm.getTeams().length; i++) {
				System.out.println(Arrays.toString(gm.getTeams()[i].getPlayers()));
			}
			System.out.println("\n");
		});
		return spawnplayer ;

	}
	private Button testremoveALLplayer() {
		Button spawnplayer = new Button("Remove all players");
		spawnplayer.setOnAction(e->{
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j <20; j++) {
					gm.getTeams()[i].getPlayers()[j] = null;
				}
			}
		});
		return spawnplayer ;

	}
	private Button testServerGame() {
		Button gameThreads = new Button("run server game threads");
		gameThreads.setOnAction(e->{
			server = new ServerGameRunnable(gm, guiTeams);
			Thread t = new Thread(server);
			t.start();
		});
		return gameThreads;
	}
	public void close() {
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
			server.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println("failed to close server");
		}
	}

}
