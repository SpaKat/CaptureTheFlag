package CaptureTheFlagGame;

public class Player extends GameColorObject{
	
	
	private double heading; // tan2
	private Statistics stats; //custemized

	
	public Player(Statistics stats,int color) {
		setStats(stats);
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
	public void setStats(Statistics stats) {
		this.stats = stats;
	}
	
	
}
