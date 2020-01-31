package CaptureTheFlagGame;
public class Calculations {


	private static double maxX;
	private static double maxY;

	
	public static double calculateUnitsPerTick(double speed) {
		double unitsmovedpertick = speed/20;
		return unitsmovedpertick;
	}
	public static boolean hitTheEdge(GameObject o) {
		boolean b = false;
		if (o.getX() <= 0) {
			b = true;
		}
		if (o.getY() <= 0) {
			b = true;
		}
		if (o.getX() >= maxX) {
			b = true;
		}
		if (o.getY() >= maxY) {
			b = true;
		}
		return b;
	}
	
	public static void move(double speed, double heading, GameObject o) {
			//o.setX(  o.getX() + Math.pow(Math.cos(heading),2)*calculateUnitsPerTick(speed)  );
			//o.setY(  o.getY() + Math.pow(Math.sin(heading),2)*calculateUnitsPerTick(speed)  );
		double dx = Math.cos(heading)*calculateUnitsPerTick(speed);
		if (o.getX()+dx>maxX) {
			//dx = maxX-dx;
			dx = 0;
			o.setX(maxX);
		}
		if (o.getX()+dx<0) {
			//dx = - o.getX();
			o.setX(0);
		}
		
		o.setX(  o.getX() + dx  );
		
		double dy =  Math.sin(heading)*calculateUnitsPerTick(speed);
		
		if (o.getY()+dy>maxY) {
		//	dy = maxY-dy;
			dy = 0;
			o.setY(maxY);
		}
		if (o.getY()+dy<0) {
		//	dy = - o.getY();
			dy = 0;
			o.setY(0);
		}
		
		o.setY(  o.getY() + dy  );
	
	}
	public static double unitsMoved(double speed, double heading) {
		double dx = Math.cos(heading)*calculateUnitsPerTick(speed)  ;
		double dy = Math.sin(heading)*calculateUnitsPerTick(speed)  ;
		return Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
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
		double speed = 50;
		x = x +Math.cos(heading)*calculateUnitsPerTick(speed );
		y = y +Math.sin(heading)*calculateUnitsPerTick(speed);

		System.out.println(heading/Math.PI*180);
		System.out.println(Math.cos(heading)*calculateUnitsPerTick(speed) + " " + Math.sin(heading)*calculateUnitsPerTick(speed));
		System.out.println(x  +"   " + y);
		System.out.println(unitsMoved(speed, heading));
	}
	public static void relocate(double x, double y) {
		maxX = x;
		maxY = y;
	}
	public static void setY(double y) {
		maxY = y;
	}
	public static void setX(double x) {
		maxX = x;
		
	}
}
