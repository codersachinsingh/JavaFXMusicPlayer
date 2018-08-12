package application;



import javafx.application.Application;
import javafx.stage.Stage;
import musicplayer.media.MediaFileScanner;
import musicplayer.media.MediaLocator;
import musicplayer.media.MediaScanLocations;

import java.util.List;


public class Main extends Application {
	
    public static void main(String[] args) {
        MediaLocator locator =  MediaScanLocations.getInstance();
        MediaFileScanner mediaFileScanner = new MediaFileScanner(locator);
        mediaFileScanner.addAllowedExtension("mp3");
        //mediaFileScanner.removeAllowedExtention("jpg");
        mediaFileScanner.getMediaLocator().getMediaScanLocations().add("D:\\My Media\\My Music\\Bhojpoori Songs");
        List<String> list = mediaFileScanner.getAllMediaFiles();
        for (String s : list)
            System.out.println(s);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    }
       
}
