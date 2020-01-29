package Gui;
import CaptureTheFlagGame.Player;
import Gui.ColorHexConveter;
import javafx.scene.shape.Circle;

public class GameGUIPlayer extends Circle {
	
	private Player player;
	
	public GameGUIPlayer(Player player) {
		this.player = player;
		setRadius(player.getRadius());
		setStyle("-fx-fill: "+new ColorHexConveter(player.getColor()));
	}

	public void relocatePOS() {
		setLayoutX(player.getX());
		setLayoutY(player.getY());
	}

}
