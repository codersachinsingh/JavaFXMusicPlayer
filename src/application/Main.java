package application;



import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import musicplayer.media.*;
import musicplayer.metadata.Song;
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
import java.util.Iterator;
import java.util.List;


public class Main extends Application {

    public static void main(String[] args) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        MediaLocator locator = MediaScanLocations.getInstance();
        locator.getMediaScanLocations().add("D:\\My Media\\My Music\\Music");
        MediaScanner scanner = new MediaFileScanner(locator);
        System.out.println(scanner.getAllMediaFiles().size());
        for (String s : locator.getMediaScanLocations())
        System.out.println(s);
        Library library = new MediaLibrary();
        LibraryEngine libraryEngine = new MediaLibraryEngine(scanner, library);
        for (String s : library.getSongs().keySet()) {
            System.out.println(library.getSongs().get(s));
        }
    	
    }

}