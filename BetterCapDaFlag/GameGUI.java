

import CaptureTheFlagGame.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameGUI extends Application {

	
	private BorderPane mainBorderPane;
	private GameManager gameManager;
	private GameBoardPane gameBoardPane;
	
	public GameGUI() {
		
		double startX = 300;
		double startY = 300;
		
		gameManager = new GameManager(startX, startY);
		gameBoardPane = new GameBoardPane(gameManager);
		
		
		
		mainBorderPane = new BorderPane(gameBoardPane);
		mainBorderPane.setTop(new GameGUIControls(gameManager,gameBoardPane));
		gameBoardPane.setPrefWidth(startX);
		gameBoardPane.setPrefHeight(startY);
		gameBoardPane.autosize();
		
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Capture The Flag");
		stage.setOnCloseRequest(close ->{
			
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
