package Gui;
import CaptureTheFlagGame.Team;
import Client.GameClient;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameGUIClientControls extends Stage {

	private BorderPane bp = new BorderPane();
	private GameClient gameClient;
	public GameGUIClientControls(GameClient gameClient) {
		super();
		this.gameClient = gameClient;
		startUp();
		setScene(new Scene(bp,200,200));
		setTitle("Client Controls");
		setOnCloseRequest(clo ->{
			gameClient.interrupt();
		});
		show();
	}

	private void startUp() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		ComboBox<Label> teams = new ComboBox<>();

		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);

		timeline.getKeyFrames().add(new KeyFrame(Duration.ONE, e ->  {
			try {
				if(!(gameClient.getGameInfo() == null)) {
					System.out.println(gameClient.getGameInfo().toString());
					setUpComboBox(teams);
					timeline.stop();
				}
			}catch (Exception ek) {
				//ek.printStackTrace();
			}
		}));
		timeline.play();

		Button selectTeam = new Button("Select Team");
		selectTeam.setOnAction(e->{
			try {
				int teamId = Integer.parseInt(teams.getValue().getText().split(" ")[1]);
				System.out.println("TeamID_"+teamId);
				gameClient.selectTeam(teamId);
			}catch (Exception e3) {
				// TODO: handle exception
			}
		});
		vbox.getChildren().addAll(teams,selectTeam);
		bp.setCenter(vbox);

	}

	private void setUpComboBox(ComboBox<Label> teams) {
		for (int i = 0; i < gameClient.getGameInfo().getTeams().size(); i++) {
			Team team = gameClient.getGameInfo().getTeams().get(i);
			if(!team.full()) {
				String teamID = "Team " + team.getId();
				Label teamText = new Label(teamID);
				teamText.setStyle("-fx-text-fill: " + new ColorHexConveter(team.getColor()));
				teams.getItems().add(teamText);
			}
		}
	}

}
