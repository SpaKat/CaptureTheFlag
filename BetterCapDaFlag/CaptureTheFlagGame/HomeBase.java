package CaptureTheFlagGame;

public class HomeBase extends GameColorObject{


	private static final long serialVersionUID = -3207956408600538987L;
	private double radius; 
	
	public HomeBase( int color) {
		setColor(color);
		radius = 50;
	}

	public double getRadius() {
		// TODO Auto-generated method stub
		return radius;
	}
	
}
