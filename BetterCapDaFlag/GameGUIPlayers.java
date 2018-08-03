import CaptureTheFlagGame.Player;
import javafx.scene.shape.Circle;

public class GameGUIPlayers extends Circle {
	
	private Player player;
	
	public GameGUIPlayers(Player player) {
		this.player = player;
		setRadius(player.getRadius());
		setStyle("-fx-fill: "+new ColorHexConveter(player.getColor()));
	}

	public void relocatePOS() {
		setLayoutX(player.getX());
		setLayoutY(player.getY());
	}

}
