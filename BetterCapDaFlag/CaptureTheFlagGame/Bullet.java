package CaptureTheFlagGame;

public class Bullet extends GameColorObject {

	private static final long serialVersionUID = 3115673913364875572L;
	private double speed;
	private double heading; // tan2
	private boolean died = false;
	private double range;
	private double damage;
	
	
	public Bullet(double x, double y, double heading, double range, double damage) {
		speed = 125;
		setX(x);
		setY(y);
		setRadius(3);
		this.range = range;
		this.heading = heading;
		this.damage = damage;
		setSpawned(true);
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
		return damage;
	}
	public void move() {
		Calculations.move(speed, heading, this);
		range -= Calculations.unitsMoved(speed, heading);
		if (range <= 0 || Calculations.hitTheEdge(this)) {
			setDied(true);
		}
	}

	public boolean same(Bullet o) {
		boolean b = false;
		if (o.getX() == getX() && o.getY() == getY()) {
			b = true;
		}
		return b;
	}
}
