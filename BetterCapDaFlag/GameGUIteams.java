import CaptureTheFlagGame.Team;

public class GameGUIteams {
	
	private Team team;
	private GameBoardPane gameBoardPane;
	
	private GameGUIHomeBase homebase;
	private GameGUIFlag flag;
	
	public GameGUIteams(Team team, GameBoardPane gameBoardPane) {
		this.team = team;
		this.gameBoardPane = gameBoardPane;
		homebase = new GameGUIHomeBase(team.getHomeBase());
		flag = new GameGUIFlag(team.getFlag());
		
		gameBoardPane.getChildren().addAll(homebase,flag);
	}

	public void relocate() {
		homebase.relocatePOS();
		flag.relocatePOS();
	}

	
	
	
	
}
