package musicplayer.ui.fxmls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TopNavigationBarController {
	@FXML
	AnchorPane root;
	@FXML
	Button nowPlaying;
	@FXML
	Button songs;
	@FXML
	Button albums;
	@FXML
	Button artists;
	@FXML
	Button genres;
	@FXML
	Button playLists;
	@FXML
	TextField searchbar;
	public void initialize() {
		root.getStylesheets().add(getClass().getClassLoader().getResource("musicplayer/ui/stylesheets/TopNavigationBar.css").toExternalForm());
		System.out.println("TopNavigationBar");
	}
}
