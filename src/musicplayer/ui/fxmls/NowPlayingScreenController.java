package musicplayer.ui.fxmls;

import java.util.Observable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import musicplayer.util.AppUtils;

public class NowPlayingScreenController {
	private MediaPlayer mediaplayer;
	@FXML
	private Button playPauseBtn;
	@FXML
	private Button previousBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private Button stopBtn;
	@FXML
	private ToggleButton shuffleBtn;
	@FXML
	private Button repeatBtn;
	@FXML
	private Slider playerSlider;
	@FXML
	private ProgressBar playerProgressBar;
	@FXML
	private Slider volSlider;
	@FXML
	private ProgressBar volProgressBar;
	@FXML
	private ImageView speakerIcon;
	private void setIcons() {
		ClassLoader cl = getClass().getClassLoader();
		playPauseBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/play_circle.png").toExternalForm(), 40, 40));
		previousBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/previous_track.png").toExternalForm(), 25, 25));
		stopBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/stop.png").toExternalForm(), 25, 25));
		nextBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/next_track.png").toExternalForm(), 25, 25));
		repeatBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/repeat.png").toExternalForm(), 25, 25));
		shuffleBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/shuffle.png").toExternalForm(), 25, 25));
		Image s = new Image(cl.getResource("icons/volume_mute.png").toExternalForm());
		speakerIcon.setImage(s);
		speakerIcon.setFitHeight(40);
		speakerIcon.setFitWidth(40);
	}
	
	private void bindControls() {
		playerProgressBar.prefWidthProperty().bind(playerSlider.prefWidthProperty());
		playerProgressBar.prefHeightProperty().bind(playerSlider.prefHeightProperty());
		
		volProgressBar.prefWidthProperty().bind(volSlider.prefWidthProperty());
		volProgressBar.prefHeightProperty().bind(volSlider.prefHeightProperty());
		playerProgressBar.progressProperty().bind(playerSlider.valueProperty().divide(100.0));
		volProgressBar.progressProperty().bind(volSlider.valueProperty().divide(100.0));
		
		volSlider.valueProperty().addListener((e)-> {
			double currentValue = volSlider.getValue();
			if (currentValue > 0 && currentValue <= 30) {
				Image s = new Image(getClass().getClassLoader().getResource("icons/volume_low.png").toExternalForm());
				speakerIcon.setImage(s);
			}
			else if (currentValue > 30 && currentValue <= 75) {
				Image s = new Image(getClass().getClassLoader().getResource("icons/volume_medium.png").toExternalForm());
				speakerIcon.setImage(s);
			}
			else if (currentValue > 75 && currentValue <= 100){
				Image s = new Image(getClass().getClassLoader().getResource("icons/volume_full.png").toExternalForm());
				speakerIcon.setImage(s);
			}
			else {
				Image s = new Image(getClass().getClassLoader().getResource("icons/volume_mute.png").toExternalForm());
				speakerIcon.setImage(s);
			}
		});
	}
	public void initialize() {
		setIcons();
		bindControls();
		
	}
	
	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaplayer = mediaPlayer;
		if (mediaPlayer != null) {
			mediaPlayer.setOnReady(()-> {
				
			});
		}
	}
}
