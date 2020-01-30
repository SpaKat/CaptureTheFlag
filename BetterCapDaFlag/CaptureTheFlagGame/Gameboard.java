package CaptureTheFlagGame;

public class Gameboard extends GameObject{

	private static final long serialVersionUID = 1756498793750014225L;

	public Gameboard(double x, double y) {
		setX(x);
		setY(y);
		Calculations.setX(x);
		Calculations.setY(y);
	}
	protected void spawnGameColorObject(GameColorObject gco, int id, double offset) {
		switch (id) {
		case 0:
			gco.setX(offset);
			gco.setY(offset);
			break;
		case 1:
			gco.setX(getX() - offset);
			gco.setY(getY() - offset);
			break;
		case 2:
			gco.setX(getX() - offset);
			gco.setY(offset);
			break;
		case 3:
			gco.setX(offset);
			gco.setY(getY() - offset);
			break;
		}
	}
	

}
