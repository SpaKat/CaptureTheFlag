import CaptureTheFlagGame.Team;

public class GameGUIteams {
	
	private Team team;
	private GameBoardPane gameBoardPane;
	
	private GameGUIHomeBase homebase;
	
	public GameGUIteams(Team team, GameBoardPane gameBoardPane) {
		this.team = team;
		this.gameBoardPane = gameBoardPane;
		homebase = new GameGUIHomeBase(team.getHomeBase());
		
		gameBoardPane.getChildren().add(homebase);
	}

	public void relocate() {
		homebase.relocatePOS();
		
	}

	
	
	
	
}
