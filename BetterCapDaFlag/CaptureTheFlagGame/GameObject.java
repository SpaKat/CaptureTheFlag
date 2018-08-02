package CaptureTheFlagGame;

import java.io.Serializable;

public class GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8092580588220277620L;
	
	private double x;
	private double y;

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
