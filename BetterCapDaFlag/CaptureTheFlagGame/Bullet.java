package CaptureTheFlagGame;

public class Bullet extends GameColorObject {

	private static final long serialVersionUID = 3115673913364875572L;
	private double speed;
	
	public Bullet(double x, double y) {
		speed = 50;
		setX(x);
		setY(y);
		setRadius(3);
	}
	
	public double getSpeed() {
		return speed;
	}
}
