

import CaptureTheFlagGame.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameGUI extends Application {

	
	private BorderPane mainBorderPane;
	private GameManager gameManager;
	private GameBoardPane gameBoardPane;
	private GameGUIControls controls;
	
	public GameGUI() {
		double startX = 300;
		double startY = 300;
		gameManager = new GameManager(startX, startY);
		gameBoardPane = new GameBoardPane(gameManager);
		mainBorderPane = new BorderPane(gameBoardPane);
		controls = new GameGUIControls(gameManager,gameBoardPane);
		mainBorderPane.setTop(controls);
		gameBoardPane.setPrefWidth(startX);
		gameBoardPane.setPrefHeight(startY);
		gameBoardPane.autosize();
	}
	
	
	@Override
	public void start(Stage stage){
		stage.setTitle("Capture The Flag");
		stage.setOnCloseRequest(close ->{
			controls.close();
		});
		Scene scene = new Scene(mainBorderPane);
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
