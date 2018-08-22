package musicplayer.util;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;


public class AppUtils {
    public static String APPLICATION_NAME = "JavaFXMusicPlayer";
    public static String ALLOWED_EXTENSIONS_FILE = "Scan_Able_Extensions";
    public static String SCAN_LOCATION_FILE = "MediaSearchLocations";
    public static Image DEFAULT_ALBUM_ARTWORK = new Image(new File("D:\\ashwani.jpg").toURI().toString());
    public static String DEFAULT_ALBUM_ARTWORK_LOCATION = "D:/ashwani.jpg";
    public static String ArtworksFolder = "AlbumArtworks";
    public static File AlbumArtworkFolder() {
    	String appDataDir = System.getenv("LOCALAPPDATA");

        /* Create JavaFXMusicPlayer own Directory. */
        File artworkDir = new File(appDataDir + "/" + APPLICATION_NAME + "/" +ArtworksFolder);

        if (!artworkDir.exists()) {
            boolean mkdirs = artworkDir.mkdirs();
            if (mkdirs)
                System.out.println("Directories created successfully");
        }
        return artworkDir;
    }
    
    public static ImageView getImageView(String url , int width , int height) {
    	Image image = new Image(url);
    	ImageView imageView = new ImageView(image);
    	imageView.setFitHeight(height);
    	imageView.setFitWidth(width);
    	return imageView;
    }
    
    public static String formatTime(Duration duration) {
		int seconds = (int)duration.toSeconds();
		if (seconds >= 60) {
			int minutes;
			minutes = seconds/60;
			seconds = seconds%60;
			if (minutes >= 60) {
				int hours;
				hours = minutes/60;
				minutes = minutes%60;
				return String.format("%d:%02d:%02d", hours,minutes,seconds);
			}
			else {
				return String.format("%d:%02d:%02d",0,minutes,seconds);
			}
			
		}
		else {
			return String.format("%d:%02d:%02d", 0,0,seconds);
		}
	}
}
