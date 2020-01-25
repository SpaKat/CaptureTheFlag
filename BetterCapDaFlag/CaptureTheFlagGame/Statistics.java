package CaptureTheFlagGame;

import java.io.Serializable;

public class Statistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5103534241630958413L;
	private double attack;
	private double defense;
	private double health;
	private double MAXhealth;
	private double movespeed;
	private final double MAXRATING = 100;
	
	public Statistics(double attack,double defense,double health,double movespeed) {
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		MAXhealth = health;
		this.movespeed = movespeed;
	}
	public boolean getRateing() {
		int rateing = (int)   Math.ceil(attack + defense + health + movespeed);
		boolean b = false;
		if(rateing <= MAXRATING) {
			b = true;
		}
		return b;
	}
	public double getAttack() {
		return attack;
	}
	public double getDefense() {
		return defense;
	}
	public double getHealth() {
		return health;
	}
	public double getMovespeed() {
		return movespeed;
	}
	public void fullHealth() {
		health = MAXhealth;
	}
	public void takeDamage(double damage) {
		health = health - (damage-defense/2);
	}
}
