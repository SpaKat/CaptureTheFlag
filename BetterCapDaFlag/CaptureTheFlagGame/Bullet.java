package CaptureTheFlagGame;

public class Bullet extends GameColorObject {

	private double speed;
	
	public Bullet(double x, double y) {
		speed = 50;
		setX(x);
		setY(y);
	}
	
	public double getSpeed() {
		return speed;
	}
}
