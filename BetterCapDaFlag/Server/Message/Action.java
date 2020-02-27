package Server.Message;

import java.io.Serializable;

public class Action extends Message implements Serializable {

	private double heading;
	private boolean fire;
	public Action(long id, double heading, boolean fire) {
		super(id);
		this.heading = heading;
		this.fire = fire;
	}

	public double getHeading() {
		return heading;
	}
	public boolean isFire() {
		return fire;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1329220917149977674L;

}
