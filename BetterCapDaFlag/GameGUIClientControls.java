import Client.GameClient;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameGUIClientControls extends Stage {

	private BorderPane bp = new BorderPane();
	public GameGUIClientControls(GameClient gameClient) {
		super();
		setScene(new Scene(bp));
		setTitle("Client Controls");
		
		show();
	}

}
