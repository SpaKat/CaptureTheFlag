package CaptureTheFlagGame;

public class Player extends GameColorObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4249695451161564331L;
	private double heading; // tan2
	private Statistics stats; //Customized
	private double radius = 10;
	
	public Player(Statistics stats,int color) {
		this.stats = stats;
		setColor(color);
	}
	
	public boolean isDied() {
		boolean died = false;
		if (stats.getHealth()<=0) {
			died = true;
		}
		return died;
	
	}
	
	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public Statistics getStats() {
		return stats;
	}
	public double getRadius() {
		return radius;
	}
}
