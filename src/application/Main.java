package application;



import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import musicplayer.media.*;
import musicplayer.metadata.MusicFile;
import musicplayer.metadata.Song;
import musicplayer.ui.fxmls.MainController;
import musicplayer.util.AppUtils;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.images.Artwork;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
    	/*FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("test.fxml"));
    	AnchorPane root = loader.load();
    	TestController testController = loader.getController();
    	testController.listView.setCellFactory(new Callback<ListView<MusicFile>, ListCell<MusicFile>>() {

			@Override
			public ListCell<MusicFile> call(ListView<MusicFile> param) {
				return new ListCell<MusicFile>() {
					@Override
					public void updateItem(MusicFile song , boolean empty) {
						super.updateItem(song, empty);
						System.out.println(song);
						HBox cell = null;
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("song.fxml"));
						try {
							cell = loader.load();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SongController controller = loader.getController();
						if (song == null || empty) {
							
						}
						else {
							controller.title.setText(song.getTitle());
							controller.artist.setText(song.getArtist().getArtistName());
							controller.duration.setText(AppUtils.formatTime(Duration.seconds(song.getDuration())));
							controller.albumart.setImage(song.getAlbum().getAlbumArtwork(50,50));
							setGraphic(cell);
						}
					}
				};
			}
    		
		});
    	MediaLocator mediaLocator = MediaScanLocations.getInstance();
    	MediaScanner mediaScanner = new MediaFileScanner(mediaLocator);
    	mediaScanner.addAllowedExtension("mp3");
    	Library library = new MediaLibrary();
    	MediaLibraryEngine mediaLibraryEngine = new MediaLibraryEngine(mediaScanner, library);
    	ObservableList<MusicFile> songs = library.getAllSongs();
    	for (MusicFile string : songs) {
    		System.out.println(string);
    		testController.listView.getItems().add(string);
    	}
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	*/
    	
    	
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/main.fxml"));
        try {
			BorderPane root = loader.load();
			MainController mainController = loader.getController();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			decoratePrimaryStage(primaryStage);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
   
    private void decoratePrimaryStage(Stage primaryStage) {
    	primaryStage.setTitle("JavaFXMusicPlayer");
    	primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("icons/app.png").toExternalForm()));
		primaryStage.setFullScreenExitHint("Enjoy Your Music in Full Screen. Press F11 to Exit.");
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (event)-> {
			if (event.getCode() == KeyCode.F11) {
				if (primaryStage.isFullScreen())
					primaryStage.setFullScreen(false);
				else
					primaryStage.setFullScreen(true);
			}
		});
		primaryStage.show();
    }

}