package musicplayer.ui.fxmls;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MainController {
	@FXML
	private AnchorPane bottomBar;
	@FXML
	private NowPlayingBarController bottomBarController;
	@FXML
	private AnchorPane topBar;
	@FXML
	private TopNavigationBarController topBarController;
	@FXML
	private AnchorPane centerSpace;
	
	public void initialize() {
		System.out.println("MainController");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/NowPlayingScreen.fxml"));
		
		try {
			AnchorPane screen = loader.load();
			screen.prefHeightProperty().bind(centerSpace.prefHeightProperty());
			screen.prefWidthProperty().bind(centerSpace.prefWidthProperty());
			centerSpace.getChildren().add(screen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
