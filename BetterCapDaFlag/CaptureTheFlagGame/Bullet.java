package CaptureTheFlagGame;

public class Bullet extends GameColorObject {

	private static final long serialVersionUID = 3115673913364875572L;
	private double speed;
	private double heading; // tan2
	private boolean died = false;
	private double range;
	private double damage;
	
	
	public Bullet(double x, double y, double heading, double range, double damage) {
		speed = 50;
		setX(x);
		setY(y);
		setRadius(3);
		this.range = range;
		this.heading = heading;
		this.damage = damage;
	}
	
	public double getSpeed() {
		return speed;
	}
	public double getHeading() {
		return heading;
	}
	public void setDied(boolean died) {
		this.died = died;
	}
	public boolean isDied() {
		return died;
	}

	public double getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
}
