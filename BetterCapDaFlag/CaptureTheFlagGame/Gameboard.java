package CaptureTheFlagGame;

public class Gameboard extends GameObject{

	private static final long serialVersionUID = 1756498793750014225L;

	public Gameboard(double x, double y) {
		setX(x);
		setY(y);
	}

	protected void spawnHomeBase(HomeBase homeBase, int id) {
		switch (id) {
		case 0:
			homeBase.setX(0);
			homeBase.setY(0);
			break;
		case 1:
			homeBase.setX(getX());
			homeBase.setY(getY());
			break;
		case 2:
			homeBase.setX(getX());
			homeBase.setY(0);
			break;
		case 3:
			homeBase.setX(0);
			homeBase.setY(getY());
			break;
		}
	}

	protected void spawnFlags(Flag flag, int id, double offset) {
		switch (id) {
		case 0:
			flag.setX(offset);
			flag.setY(offset);
			break;
		case 1:
			flag.setX(getX() - offset);
			flag.setY(getY() - offset);
			break;
		case 2:
			flag.setX(getX() - offset);
			flag.setY(offset);
			break;
		case 3:
			flag.setX(offset);
			flag.setY(getY() - offset);
			break;
		}
	}


}
