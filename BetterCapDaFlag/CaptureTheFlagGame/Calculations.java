package CaptureTheFlagGame;
public class Calculations {

	
	public static double distance(GameObject o1, GameObject o2) {
		double dx = Math.abs( o1.getX() - o2.getX() );
		double dy = Math.abs( o1.getY() - o2.getY() );
		double distance = Math.sqrt( Math.pow(dx, 2) + Math.pow(dy, 2));
		return distance;
	}
	public static double combineRadius(GameColorObject o1, GameColorObject o2) {
	
		return o1.getRadius() + o2.getRadius();
	}
}
