package musicplayer.ui.fxmls;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import musicplayer.util.AppUtils;

public class NowPlayingBarController {
	@FXML
	private AnchorPane root;
	@FXML
	private HBox leftHBox;
	@FXML
	private HBox rightHBox;
	@FXML
	private VBox labelsBox;
	@FXML
	private Label title;
	@FXML
	private Label albumArtist;
	@FXML
	private Label genre;
	@FXML
	private Button playButton;
	@FXML
	private ImageView albumart;
	@FXML
	private ProgressIndicator progressIndicator;
	
	
	public void initialize() {
		System.out.println("NowPlayingBarController");
		File file = new File("C:\\Users\\admin\\AppData\\Local\\JavaFXMusicPlayer\\AlbumArtworks\\Hostel (DjPunjab.CoM).jpg");
		Image image = new Image(file.toURI().toString());
		albumart.setImage(image);
	}
}
