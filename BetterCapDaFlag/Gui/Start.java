package Gui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Start extends Application {
	
	private GameMainPane mainPane;
	
	public Start() {
		mainPane = new GameMainPane();
	}
	
	
	@Override
	public void start(Stage stage){
		stage.setTitle("Capture The Flag");
		stage.setOnCloseRequest(close ->{
			mainPane.close();
		});
		Scene scene = new Scene(mainPane);
		stage.setScene(scene);
		stage.initStyle(StageStyle.DECORATED);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
