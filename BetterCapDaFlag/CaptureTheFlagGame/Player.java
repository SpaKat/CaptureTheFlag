package CaptureTheFlagGame;

public class Player extends GameColorObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4249695451161564331L;
	private double heading; // tan2
	private Statistics stats; //Customized
	private boolean respawning = false;
	private long diedAt; 
	private Bullet[] bullets;
	public Player(Statistics stats,int color) {
		this.stats = stats;
		setColor(color);
		setRadius(10);
		bullets = new Bullet[1];
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
	public void setRespawning(boolean respawn) {
		respawning = respawn;
	}
	public boolean isRespawning() {
		return respawning;
	}
	public void setBullets(Bullet[] bullets) {
		this.bullets = bullets;
	}
	public Bullet[] getBullets() {
		return bullets;
	}
	public void diedAt() {
		diedAt = System.currentTimeMillis();
		setX(-1000);
		setY(-1000);
	}

	public boolean readyToRespawn(long respawnTimer) {
		boolean b = false;
		if (Math.abs(diedAt - System.currentTimeMillis()) >= respawnTimer) {
			b = true;	
		}
		return b;
	}

	public void fullHealth() {
		stats.fullHealth();
	}

	public void move() {
		Calculations.move(stats.getMovespeed(), heading, this);
	}

	public void bullets() {
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].move();
		}
	}

	public void cleanDiedBullets() {
		for (int i = 0; i < bullets.length; i++) {
			try{
				if (bullets[i].isDied()) {
					bullets[i] = null;
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println("player cleanDiedBullets()");
			}
		}
	}
}
