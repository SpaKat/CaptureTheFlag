package Client;

import java.io.IOException;

import CaptureTheFlagGame.Statistics;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestClientGUI extends Application{

	ClientAPI api;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		api = new ClientAPI("127.0.0.1", 8008);
		api.setupPlayer(new Statistics(20, 20, 20, 30) ,(int) (Math.random()*4),"BOT");
		Scene scene = new Scene(pane);
		scene.setOnKeyPressed(e->{
			switch (e.getCode()) {
			case UP:
				try {
					api.sendAction(Math.PI*3/2,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case DOWN:
				try {
					api.sendAction(Math.PI/2,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case LEFT:
				try {
					api.sendAction(Math.PI,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case RIGHT:
				try {
					api.sendAction(0,false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case SPACE:
				try {
					api.sendFire();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			default:
				break;
			}
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
