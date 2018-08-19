package musicplayer.ui.fxmls;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {
	@FXML
	private BorderPane root;
	@FXML
	private AnchorPane bottomBar;
	@FXML
	private NowPlayingBarController bottomBarController;
	@FXML
	private AnchorPane topBar;
	@FXML
	private TopNavigationBarController topBarController;
	@FXML
	private AnchorPane centerScreens;
	@FXML
	private CenterScreensController centerScreensController;
	
	public void initialize() {
		System.out.println("MainController");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/NowPlayingScreen.fxml"));
		
		try {
			AnchorPane screen = loader.load();
			centerScreens.getChildren().add(screen);
			AnchorPane.setTopAnchor(screen, 0.0);
			AnchorPane.setBottomAnchor(screen, 0.0);
			AnchorPane.setRightAnchor(screen, 0.0);
			AnchorPane.setLeftAnchor(screen, 0.0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
