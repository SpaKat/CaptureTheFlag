package Gui_old;
import CaptureTheFlagGame.HomeBase;
import Gui.ColorHexConveter;
import javafx.scene.shape.Circle;

public class GameGUIHomeBase extends Circle {

	private HomeBase homeBase;
	
	public GameGUIHomeBase(HomeBase homeBase) {
		this.homeBase = homeBase;
		setStyle("-fx-fill: black;"
				+"-fx-stroke: " + new ColorHexConveter(homeBase.getColor()).toString() +";"
				+ "-fx-stroke-width: 1;");
		setRadius(homeBase.getRadius());
		relocatePOS();
	}
	public void relocatePOS() {
		this.toBack();
		setLayoutX(homeBase.getX());
		setLayoutY(homeBase.getY());
	}

	
	
}
