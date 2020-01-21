package CaptureTheFlagGame;
 
public class Flag extends GameColorObject{

	private static final long serialVersionUID = -5128926934579890744L;
	private boolean taken;
	private double radius;
	
	
	public Flag(int color) {
		super();
		taken = false;
		setColor(color);
		radius = 7;
	}
	
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public double getRadius() {
		return radius;
	}
}
