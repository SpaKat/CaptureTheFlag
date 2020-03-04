package Gui;

import CaptureTheFlagGame.Bullet;
import javafx.scene.shape.Circle;

public class GameGUIBullet extends Circle {

private Bullet bullet;
	
	public void setup(Bullet bullet) {
		this.bullet = bullet;
		setRadius(bullet.getRadius());
		setStyle("-fx-fill:  white;");
		
	}

	public void relocatePOS() {
		setLayoutX(bullet.getX());
		setLayoutY(bullet.getY());
	}
	public Bullet getBullet() {
		return bullet;
	}	
}
