package CaptureTheFlagGame;

public class Flag extends GameObject{

	private boolean taken;
	private int color;
	
	public Flag(int color) {
		taken = false;
		this.color = color;
	}
	
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getColor() {
		return color;
	}
	
}
