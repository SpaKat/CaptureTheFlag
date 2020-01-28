package CaptureTheFlagGame;
public class Calculations {

	
	// ? how many units per tick
	
	public static double calculateUnitsPerTick(double speed) {
		double unitsmovedpertick = speed/20;
		return unitsmovedpertick;
	}
	public static void move(double speed, double heading, GameObject o) {
		o.setX(  o.getX() + Math.pow(Math.cos(heading),2)*calculateUnitsPerTick(speed)  );
		o.setY(  o.getY() + Math.pow(Math.sin(heading),2)*calculateUnitsPerTick(speed)  );
	}
	public static double unitsMoved(double speed, double heading) {
		double dx = Math.pow(Math.cos(heading),2)*calculateUnitsPerTick(speed)  ;
		double dy = Math.pow(Math.sin(heading),2)*calculateUnitsPerTick(speed)  ;
		return dx+dy;
	}
	public static double distance(GameObject o1, GameObject o2) {
		double dx = Math.abs( o1.getX() - o2.getX() );
		double dy = Math.abs( o1.getY() - o2.getY() );
		double distance = Math.sqrt( Math.pow(dx, 2) + Math.pow(dy, 2));
		return distance;
	}
	public static double combineRadius(GameColorObject o1, GameColorObject o2) {
	
		return o1.getRadius() + o2.getRadius();
	}
	
	
	public static void main(String[] args) {
		double x = 45;
		double y = 45;
		double heading = Math.PI/4;
		x = x +Math.pow(Math.cos(heading),2)*20;
		y = y +Math.pow(Math.sin(heading),2)*20;

		System.out.println(heading/Math.PI*180);
		System.out.println(Math.pow(Math.cos(heading),2) + " " + Math.pow(Math.sin(heading),2));
		System.out.println(x  +"   " + y);
		System.out.println(unitsMoved(50, heading));
	}
	
}
