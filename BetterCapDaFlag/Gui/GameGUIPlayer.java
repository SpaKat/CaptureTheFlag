package Gui;
import CaptureTheFlagGame.Player;
import Gui.ColorHexConveter;
import javafx.scene.shape.Circle;

public class GameGUIPlayer extends Circle {
	
	private Player player;
	
	public void setup(Player player) {
		this.player = player;
		setRadius(player.getRadius());
		int border = 3 ;
		setStyle("-fx-fill: " + new ColorHexConveter(player.getColor()).toString() +";"
				+"-fx-stroke: white;"
				+ "-fx-stroke-width:" + border   +  ";");
		
	}

	public void relocatePOS() {
		setLayoutX(player.getX());
		setLayoutY(player.getY());
	//	System.out.println(player.getX() +" " + player.getY());
	}
	public Player getPlayer() {
		return player;
	}

}
