package musicplayer.ui.fxmls;

import java.io.File;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import musicplayer.util.AppUtils;

public class NowPlayingBarController {
	private MediaPlayer mediaplayer;
	@FXML
	AnchorPane root;
	@FXML
	HBox leftHBox;
	@FXML
	HBox rightHBox;
	@FXML
	VBox labelsBox;
	@FXML
	Label titleLbl;
	@FXML
	Label albumArtistLbl;
	@FXML
	Label genreLbl;
	@FXML
	Label durationLbl;
	@FXML
	Button playButton;
	@FXML
	ImageView albumart;
	@FXML
	ProgressIndicator progressIndicator;
	
	public void initialize() {
		playButton.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
	}
		
	public void setMetaData(ObservableMap<String, Object> metadata , String mediaSource) {
		// setting the album art
		Image image = (Image) metadata.get("image");
		if (image != null) {
			albumart.setImage(image);
		}
		else {
			albumart.setImage(AppUtils.DEFAULT_ALBUM_ARTWORK);
		}
		
		String title = (String) metadata.get("title");
		if (title == null)
			title = mediaSource;
		titleLbl.setText(title);
		String artist = (String) metadata.get("artist");
		if (artist == null)
			artist = "No Info";
		String album = (String) metadata.get("album");
		if (album == null)
			album = "No Info";
		albumArtistLbl.setText(album + " - " + artist);
		String genre = (String) metadata.get("genre");
		if (genre == null)
			genre = "Unknown";
		Integer year = (Integer) metadata.get("year");
		if (year == null)
			year = 0;
		genreLbl.setText(genre + " - " + year);
		
	}
}
