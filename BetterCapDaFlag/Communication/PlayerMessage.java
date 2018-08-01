package Communication;

public class PlayerMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4515689179150010955L;

	private double x;
	private double y;
	private double heading;
	private int team;
	private boolean defender;
	
	public PlayerMessage(int id) {
		super(id);
	}	

}
