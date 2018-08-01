import CaptureTheFlagGame.HomeBase;
import javafx.scene.shape.Circle;

public class GameGUIHomeBase extends Circle {

	private HomeBase homeBase;
	
	public GameGUIHomeBase(HomeBase homeBase) {
		this.homeBase = homeBase;
		
		
		
		setStyle("-fx-fill: black;"
				+"-fx-stroke: " + new ColorHexConveter(homeBase.getColor()).toString() +";"
				+ "-fx-stroke-width: 10;");
		System.out.println(new ColorHexConveter(homeBase.getColor()).toString());
		setRadius(homeBase.getRadius());
		relocatePOS();
	}
	public void relocatePOS() {
		setLayoutX(homeBase.getX());
		setLayoutY(homeBase.getY());
	}

	
	
}
