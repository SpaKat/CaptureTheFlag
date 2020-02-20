package Server.Message;

import java.io.Serializable;

public class Heading extends Message implements Serializable {

	private double heading;
	
	public Heading(long id, double heading) {
		super(id);
		this.heading = heading;
	}

	public double getHeading() {
		return heading;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1329220917149977674L;

}
