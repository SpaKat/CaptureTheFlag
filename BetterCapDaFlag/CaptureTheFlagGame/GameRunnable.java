package CaptureTheFlagGame;

public class GameRunnable implements Runnable{

	private GameManager gm;
	private boolean running;
	private long updateInterval = 20;
	public GameRunnable(GameManager gm) {
		this.gm = gm;
		running = true;
	}


	@Override
	public void run() {
		while(gm.OneTurn() && running) {
			try {
				Thread.sleep(updateInterval);
			} catch (Exception e) {
				System.err.println("GameRunnable woke up");
			}
		}
		System.out.println("Game Update Thread over");
	}

}
