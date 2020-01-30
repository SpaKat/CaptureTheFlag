package CaptureTheFlagGame;

public class Flag extends GameColorObject{

	private static final long serialVersionUID = -5128926934579890744L;
	private boolean taken;
	private Player carryingPlayer;

	public Flag(int color) {
		super();
		taken = false;
		setColor(color);
		setRadius(7);
	}

	public boolean isTaken() {
		return taken;
	}
	public void setCarryingPlayer(Player carryingPlayer) {
		this.carryingPlayer = carryingPlayer;
	}
	public Player getCarryingPlayer() {
		return carryingPlayer;
	}
	public void move() {
		if (carryingPlayer != null) {
			if (!carryingPlayer.isDied()) {
				setX( carryingPlayer.getX() );
				setY( carryingPlayer.getY() );
			}else {
				reset();
			}
		}
	}

	public void reset() {
		carryingPlayer = null;
		taken = false;
	}

	public void grabed(Player player) {
		carryingPlayer = player;
		taken = true;
	}
}
