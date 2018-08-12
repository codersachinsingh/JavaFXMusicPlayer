package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.stage.Stage;
import musicplayer.media.MediaFileExtensionFilter;
import musicplayer.media.MediaFileScanner;
import musicplayer.media.MediaLocator;
import musicplayer.media.MediaScanLocations;

public class Main extends Application {
	
    public static void main(String[] args) {
    	
    	String file = "D:\\My Media\\My Music\\Music";
    	MediaLocator locator = MediaScanLocations.getInstance();
    	MediaFileScanner scanner = new MediaFileScanner(locator);
    	locator.getMediaScanLocations().add(file);
    	for (String string : scanner.getAllMediaFiles()) {
    		System.out.println(string);
    	}
    	//locator.getMediaScanLocations().remove(file);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    }
       
}
