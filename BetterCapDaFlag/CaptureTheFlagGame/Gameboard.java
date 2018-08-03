package CaptureTheFlagGame;

public class Gameboard extends GameObject{

	private double totalOffset =0;

	public Gameboard(double x, double y) {
		setX(x);
		setY(y);
	}

	protected void spawnHomeBase(HomeBase homeBase, int id) {
		switch (id) {
		case 0:
			homeBase.setX(totalOffset/2);
			homeBase.setY(totalOffset/2);
			break;
		case 1:
			homeBase.setX(getX() - totalOffset/2);
			homeBase.setY(getY() - totalOffset/2);
			break;
		case 2:
			homeBase.setX(getX() - totalOffset/2);
			homeBase.setY(totalOffset/2);
			break;
		case 3:
			homeBase.setX(totalOffset/2);
			homeBase.setY(getY() - totalOffset/2);
			break;
		}
	//	System.out.println(id+ "     "+homeBase.getX() + "    " + homeBase.getY());
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
