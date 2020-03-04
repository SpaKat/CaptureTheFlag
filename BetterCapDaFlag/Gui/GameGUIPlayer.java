package Gui;
import CaptureTheFlagGame.Player;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class GameGUIPlayer extends Group {
	
	private Player player;
	private Circle circle = new Circle();
	private Label label = new Label();
	
	
	public GameGUIPlayer() {
		getChildren().addAll(circle,label);
	}

	public void setup(Player player) {
		this.player = player;
		circle.setRadius(player.getRadius());
		int border = 3 ;
		circle.setStyle("-fx-fill: " + new ColorHexConveter(player.getColor()).toString() +";"
				+"-fx-stroke: white;"
				+ "-fx-stroke-width:" + border   +  ";");
		label.setText(player.getName());
		label.setStyle("-fx-font: bold 20 'Times New Roman'; -fx-text-fill: White");
		label.setLabelFor(circle);
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
