package CaptureTheFlagGame;

public class Statistics {
	
	private double attack;
	private double defense;
	private double health;
	private double movespeed;
	private int color; // hex
	
	public Statistics(double attack,double defense,double health,double movespeed,int color) {
		setAttack(attack);
		setDefense(defense);
		setHealth(health);
		setMovespeed(movespeed);
		setColor(color);
	}
	public int getRateing() {
		int rateing = (int) (attack + defense + health + movespeed); 
		return rateing;
	}
	public double getAttack() {
		return attack;
	}
	public void setAttack(double attack) {
		this.attack = attack;
	}
	public double getDefense() {
		return defense;
	}
	public void setDefense(double defense) {
		this.defense = defense;
	}
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public double getMovespeed() {
		return movespeed;
	}
	public void setMovespeed(double movespeed) {
		this.movespeed = movespeed;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	

}
