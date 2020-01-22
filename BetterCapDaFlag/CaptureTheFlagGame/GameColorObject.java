package CaptureTheFlagGame;

public class GameColorObject extends GameObject{


	private static final long serialVersionUID = 671338418795896493L;
	private int color ;
	private boolean spawned;
	private double radius; 

	
	public GameColorObject() {
		spawned = false;
	}
	public boolean isSpawned() {
		return spawned;
	}
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
}
