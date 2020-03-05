package Gui;


import java.net.Inet4Address;
import java.net.UnknownHostException;

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
		try {
			stage.setTitle("Capture The Flag at " + Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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
