package musicplayer.ui.fxmls;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.mp4.atom.Mp4HdlrBox.MediaDataType;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import musicplayer.util.AppUtils;

public class NowPlayingScreenController {
	private MediaPlayer mediaplayer = null;
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
	@FXML
	private Label speakerVolPersentage;
	@FXML
	private Label currentTimeLbl;
	@FXML 
	private Label totalTimeLbl;
	@FXML
	private ImageView bigAlbumArt;
	
	private Duration duration;
	//***********Screen Change Buttons*********************
	@FXML
	private Button nextScreen;
	@FXML
	private Button previousScreen;
	//***********Screens and their Controllers**************
	private VBox metadatainfo;
	private MetaDataInfoDisplayController mdidController;
	
	//*******************++++************
	@FXML
	private AnchorPane root;
	@FXML
	private AnchorPane bottomAnchorPane;
	//-----------------------
	@FXML
	private AnchorPane metadataPane;
	@FXML
	private AnchorPane screen;
	
	NowPlayingBarController nowPlayingBarController;
	//--------------------
	private BooleanProperty repeat = new SimpleBooleanProperty(this,"repeat",false);
	private BooleanProperty shuffle = new SimpleBooleanProperty(this,"shuffle",false);
	private boolean atEndofMedia = false;
	private boolean stoprequested = false;
	
	private void setIcons() {
		ClassLoader cl = getClass().getClassLoader();
		playPauseBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/play_circle.png").toExternalForm(), 40, 40));
		previousBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/previous_track.png").toExternalForm(), 25, 25));
		stopBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/stop.png").toExternalForm(), 25, 25));
		nextBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/next_track.png").toExternalForm(), 25, 25));
		repeatBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/repeat.png").toExternalForm(), 25, 25));
		shuffleBtn.setGraphic(AppUtils.getImageView(cl.getResource("icons/shuffle.png").toExternalForm(), 25, 25));
		Image s = new Image(cl.getResource("icons/volume_mute.png").toExternalForm());
		nextScreen.setGraphic(AppUtils.getImageView(cl.getResource("icons/next.png").toExternalForm(), 25, 25));
		previousScreen.setGraphic(AppUtils.getImageView(cl.getResource("icons/previous.png").toExternalForm(), 25, 25));
		speakerIcon.setImage(s);
		speakerIcon.setFitHeight(40);
		speakerIcon.setFitWidth(40);
	}
	private void initializeScreens() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/MetaDataInfoDisplay.fxml"));
		try {
			metadatainfo = loader.load();
			mdidController = loader.getController();
			screen.getChildren().add(metadatainfo);
			AnchorPane.setTopAnchor(metadatainfo, 0.0);
			AnchorPane.setBottomAnchor(metadatainfo, 0.0);
			AnchorPane.setLeftAnchor(metadatainfo, 0.0);
			AnchorPane.setRightAnchor(metadatainfo, 0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void bindControls() {
		// binding the progress bar dimensions
		playerProgressBar.prefWidthProperty().bind(playerSlider.prefWidthProperty());
		playerProgressBar.prefHeightProperty().bind(playerSlider.prefHeightProperty());
		volProgressBar.prefWidthProperty().bind(volSlider.prefWidthProperty());
		volProgressBar.prefHeightProperty().bind(volSlider.prefHeightProperty());
		playerProgressBar.progressProperty().bind(playerSlider.valueProperty().divide(100.0));
		volProgressBar.progressProperty().bind(volSlider.valueProperty().divide(100.0));
		
		playerSlider.setVisible(false);
		
		bigAlbumArt.fitWidthProperty().bind(bigAlbumArt.fitHeightProperty());
		bigAlbumArt.fitHeightProperty().bind(root.heightProperty().subtract(140));
		metadataPane.prefWidthProperty().bind(Bindings.createDoubleBinding(()->(root.getWidth()-bigAlbumArt.getFitWidth()-100.0), root.widthProperty() , bigAlbumArt.fitWidthProperty()));
		metadataPane.prefHeightProperty().bind(bigAlbumArt.fitHeightProperty().subtract(200));
		repeatBtn.setOnAction((event)-> {
			if (repeat.get())
			{
				repeatBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/repeat.png").toExternalForm(), 25, 25));
				repeat.set(false);
				shuffleBtn.setDisable(false);
			}
			else {
				repeatBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/repeat_one.png").toExternalForm(), 25, 25));
				repeat.set(true);
				shuffleBtn.setDisable(true);
			}
		});
		
		volSlider.valueProperty().addListener((e)-> {
			int value = (int) volSlider.getValue();
			value = value * 2;
			speakerVolPersentage.setText(value+"%");
		});
		
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
		
		root.setOnScroll((event)-> {
			double changeFactor = event.getDeltaY()/40 * 5.0;
			double currentValue = volSlider.getValue();
			double sum = changeFactor + currentValue;
			if (sum >= 0.0 || sum <= 100.0) {
				volSlider.setValue(sum);
				
				if (mediaplayer != null) mediaplayer.setVolume(sum/100.0);
			}
		});
		
		
		repeat.addListener((e)-> {
			if (mediaplayer != null) {
				mediaplayer.setCycleCount(repeat.get()?MediaPlayer.INDEFINITE:1);
			}
		});
		
	}
	public void initialize() {
		setIcons();
		bindControls();
		initializeScreens();
		
		
	}
	
	public void setMediaPlayer(MediaPlayer mp) {
		this.mediaplayer = mp;
		if (!playerSlider.isVisible())
			playerSlider.setVisible(true);
		duration = mediaplayer.getMedia().getDuration();
		
		if (duration != Duration.UNKNOWN) {
			totalTimeLbl.setText(AppUtils.formatTime(duration));
			nowPlayingBarController.durationLbl.setText("--:--/"+AppUtils.formatTime(duration));
		}
		
		playPauseBtn.setOnAction(this::haldlePlayPause);
		nowPlayingBarController.playButton.setOnAction(this::haldlePlayPause);
		playerSlider.setOnMouseClicked((event)-> {
			playerSlider.setValueChanging(true);
			double value = (event.getX()/playerSlider.getWidth()) * playerSlider.getMax();
			playerSlider.setValue(value);
			if (playerSlider.isValueChanging()) {
				mediaplayer.seek(duration.multiply(playerSlider.getValue()/100.0));
				updateControlValues();
			}
			playerSlider.setValueChanging(false);
		});
		
		stopBtn.setOnAction((event)-> {
			if (mediaplayer.getStatus() == Status.PLAYING || mediaplayer.getStatus() == Status.STALLED || mediaplayer.getStatus() == Status.PAUSED) {
				mediaplayer.stop();
				playPauseBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
				nowPlayingBarController.playButton.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
				updateControlValues();
				
			}
		});
		
		mediaplayer.currentTimeProperty().addListener((e)-> {
			updateControlValues();
		});
		playerSlider.valueProperty().addListener((e)-> {
			if (playerSlider.isValueChanging()) {
				mediaplayer.seek(duration.multiply(playerSlider.getValue()/100.0));
				updateControlValues();
			}
		});
		
		volSlider.valueProperty().addListener((e)-> {
			if (volSlider.isValueChanging()) {
				mediaplayer.setVolume(volSlider.getValue()/100.0);
			}
		});
		
		
		mediaplayer.setCycleCount(repeat.get()?MediaPlayer.INDEFINITE:1);
		mediaplayer.setOnPlaying(()-> {
			playPauseBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/pause_circle.png").toExternalForm(), 40, 40));
			nowPlayingBarController.playButton.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/pause_circle.png").toExternalForm(), 40, 40));
		});
		mediaplayer.setOnPaused(()-> {
			playPauseBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
			nowPlayingBarController.playButton.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
		});
		mediaplayer.setOnEndOfMedia(()-> {
			if (!repeat.get()) {
				playPauseBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
				nowPlayingBarController.playButton.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
				playerSlider.setValue(0.0);
				atEndofMedia = true;
				// request next track.
			}
		});
		setMetaData(mediaplayer.getMedia().getMetadata(),mediaplayer.getMedia().getSource());
		nowPlayingBarController.setMetaData(mediaplayer.getMedia().getMetadata(), mediaplayer.getMedia().getSource());
		updateControlValues();
		
	}
	
	private void updateControlValues() {
		Platform.runLater(()-> {
			Duration currentTime = mediaplayer.getCurrentTime();
			String current = AppUtils.formatTime(currentTime);
			currentTimeLbl.setText(current);
			nowPlayingBarController.durationLbl.setText(current+"/"+AppUtils.formatTime(duration));
			playerSlider.setVisible(!duration.isUnknown());
			if (playerSlider.isVisible() && duration.greaterThan(Duration.ZERO) && !playerSlider.isValueChanging()) {
				playerSlider.setValue(currentTime.divide(duration).toMillis()*100.0);
				nowPlayingBarController.progressIndicator.setProgress(currentTime.divide(duration).toMillis());
			}
			
			if (!volSlider.isValueChanging()) {
				volSlider.setValue(mediaplayer.getVolume() * 100);
			}
		});
	}
	
	public void setMetaData(ObservableMap<String, Object> metadata , String mediaSource) {
		// setting the album art
		Image image = (Image) metadata.get("image");
		if (image != null) {
			bigAlbumArt.setImage(image);
		}
		else {
			bigAlbumArt.setImage(AppUtils.DEFAULT_ALBUM_ARTWORK);
		}
		
		String title = (String) metadata.get("title");
		if (title == null)
			title = mediaSource;
		mdidController.titleArtistText().setValue(title);
		String artist = (String) metadata.get("artist");
		if (artist == null)
			artist = "No Info";
		mdidController.artistText().setValue(artist);
		String album = (String) metadata.get("album");
		if (album == null)
			album = "No Info";
		mdidController.albumText().setValue(album);
		String genre = (String) metadata.get("genre");
		if (genre == null)
			genre = "Unknown";
		Integer year = (Integer) metadata.get("year");
		if (year == null)
			year = 0;
		mdidController.genreYearText().setValue(genre + " - " + year);
		
	}
	public void setNowPlayingBarController(NowPlayingBarController nowPlayingBarController) {
		this.nowPlayingBarController = nowPlayingBarController;
	}
	
	private void haldlePlayPause(ActionEvent eventHandler) {
		Status status = mediaplayer.getStatus();
		if (status == Status.UNKNOWN || status == Status.HALTED) {
			
		}
		else {
			if (status == Status.PAUSED || status == Status.STOPPED || status == Status.READY) {
				mediaplayer.play();
				updateControlValues();
				mediaplayer.currentTimeProperty().addListener((e)-> {
					updateControlValues();
				});
				playPauseBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/pause_circle.png").toExternalForm(), 40, 40));
				nowPlayingBarController.playButton.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/pause_circle.png").toExternalForm(), 40, 40));
			}
			else {
				mediaplayer.pause();
				updateControlValues();
				playPauseBtn.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
				nowPlayingBarController.playButton.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/play_circle.png").toExternalForm(), 40, 40));
			}
		}
	
	}

}
