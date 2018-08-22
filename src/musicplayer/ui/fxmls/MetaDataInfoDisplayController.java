package musicplayer.ui.fxmls;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import musicplayer.util.AppUtils;

public class MetaDataInfoDisplayController {
	@FXML
	private VBox vbox;
	@FXML
	private Label title;
	@FXML
	private Label artist;
	@FXML
	private Label album;
	@FXML
	private Label genre_year;
	@FXML
	private Label like;
	
	public void setNoInfo() {
		title.setText("Welcome To JavaFXMusicPlayer");
		album.setText("Created by ");
		genre_year.setText("Sachin Singh");
		like.setText("");
	}
	
	public void initialize() {
		title.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/music_icon.png").toExternalForm(), 30, 30));
		artist.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/artist_purple.png").toExternalForm(), 25, 25));
		album.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/music_cd.png").toExternalForm(), 25, 25));
		genre_year.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/rating_star.png").toExternalForm(), 25, 25));
		like.setGraphic(AppUtils.getImageView(getClass().getClassLoader().getResource("icons/unlike.png").toExternalForm(), 30, 30));
		setNoInfo();
		
	}
	
	public VBox getVbox() {
		return vbox;
	}
	
	public StringProperty titleArtistText() {
		return title.textProperty();
	}
	
	public StringProperty albumText() {
		return album.textProperty();
	}
	
	public StringProperty genreYearText() {
		return genre_year.textProperty();
	}
	
	public StringProperty artistText() {
		return artist.textProperty();
	}
	
	public Label likeImg() {
		return like;
	}
}
