package CaptureTheFlagGame;

public class GameColorObject extends GameObject{
	private int color ;
	private boolean spawned;
	
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
	
	
}
