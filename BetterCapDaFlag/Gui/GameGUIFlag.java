package Gui;
import CaptureTheFlagGame.Flag;
import javafx.scene.shape.Circle;

public class GameGUIFlag extends Circle {
	
	private Flag flag;

	public GameGUIFlag(Flag flag) {
		this.flag = flag;
		int border = 3;
		setStyle("-fx-fill: " + new ColorHexConveter(flag.getColor()).toString() +";"
				+"-fx-stroke: gold;"
				+ "-fx-stroke-width:" + border  +  ";");
		setRadius(flag.getRadius());
		relocatePOS();
	}

	public void relocatePOS() {
		this.toFront();
		setLayoutX(flag.getX());
		setLayoutY(flag.getY());
	}

	
}
