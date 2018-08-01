

import CaptureTheFlagGame.GameManager;
import Server.GameServer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameGUIControls extends MenuBar {

	private GameManager gameManager;
	private GameBoardPane gameBoardPane;
	private Timeline gameTimeline;
	private GameServer gameServer;
	// bullet 1/3
	// player 1/2 


	public GameGUIControls(GameManager gameManager, GameBoardPane gameBoardPane) {
		super();
		gameServer = new GameServer();
		setStyle("-fx-background-color: aqua ");
		this.gameManager = gameManager;
		this.gameBoardPane = gameBoardPane;


		gameTimeline = new Timeline();
		gameTimeline.setCycleCount(Timeline.INDEFINITE);
		gameTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000/60), test -> {
			//TODO 
			this.gameManager.relocatePieces(); // back end move
			this.gameBoardPane.relocatePieces();// front end move
			this.gameBoardPane.clean();
			
		}));
		

		Menu serverMenu = serverMenu();
		Menu clientMenu = clientMenu();
		Menu player = playerMenu();
		getMenus().addAll(serverMenu,clientMenu,player);
	}

	private Menu serverMenu() {
		Menu serverMenu = new Menu("Server Controls");
		MenuItem startServer = new MenuItem("Start Server");
		startServer.setDisable(true);
		startServer.setOnAction(start ->{
			gameBoardPane.start();
			gameTimeline.play();
		});
		MenuItem ServerSettings = new MenuItem("Server Settings");
		ServerSettings.setOnAction(settings ->{
			showSettings(startServer);
		});
		serverMenu.getItems().addAll(startServer,ServerSettings);
		return serverMenu;
	}

	private void showSettings(MenuItem startServer) {
		HBox numberofPlayershb = new HBox();
		TextField numberofPlayer = numberOfPLayers(numberofPlayershb);

		HBox numberofTeamshb = new HBox(30);
		TextField numberofTeam = numberofTeams(numberofTeamshb);

		HBox respawnhb = new HBox(40);
		TextField numberofrespawn = respawnTime(respawnhb);

		Button save = new Button("Save");
		Stage stage = new Stage();
		save.setOnAction(s ->{
			try {
			gameManager.setMaxNumofTeams(Integer.parseInt(numberofTeam.getText()));
			gameManager.setMaxPLayersPerTeam(Integer.parseInt(numberofPlayer.getText()));
			gameManager.setRespawnTimer(Integer.parseInt(numberofrespawn.getText()));
			startServer.setDisable(false);
			stage.close();
			}catch (Exception e) {
				// TODO
			}
		});
		
		VBox backVbox = new VBox();
		backVbox.setAlignment(Pos.CENTER);
		backVbox.getChildren().addAll(numberofPlayershb,numberofTeamshb,respawnhb,save);
		
		
		stage.setTitle("Server Settings");
		stage.setScene(new Scene(backVbox));
		stage.show();
	}

	private TextField respawnTime(HBox respawnhb) {
		respawnhb.setAlignment(Pos.CENTER);
		Text askForrespawn = new Text("Enter Respawn Timer in seconds");
		TextField numberofrespawn = new TextField("3");
		numberofrespawn.textProperty().addListener( (obs ,old,newV ) ->{
			try {
				int i = Integer.parseInt(newV);
				if (i < 01 ) {
					numberofrespawn.setText("3");
				}

			}catch (Exception e) {
				numberofrespawn.setText("3");
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

	private Menu clientMenu() {
		Menu clientMenu = new Menu("Client Controls");
		return clientMenu;
	}

	private Menu playerMenu() {
		Menu player = new Menu("Player Options");
		return player;
	}

	public void close() {
		gameServer.interrupt();
	}




}
