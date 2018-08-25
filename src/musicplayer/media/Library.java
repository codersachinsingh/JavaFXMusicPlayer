package musicplayer.media;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import musicplayer.metadata.*;

public interface Library {
    ObservableMap<String, MusicFile> getSongs();
    ObservableMap<String,MusicAlbum> getAlbums();
    ObservableMap<String, MusicArtist> getArtists();
    ObservableMap<String, MusicGenre> getGenres();
    
    ObservableList<MusicFile> getAllSongs();
    ObservableList<MusicAlbum> getAllAlbums();
    ObservableList<MusicArtist> getAllArtists();
    ObservableList<MusicGenre> getAllGenres();
    
    ReadOnlyIntegerProperty getTotalSongs();
    ReadOnlyIntegerProperty getTotalAlbums();
    ReadOnlyIntegerProperty getTotalArtists();
    ReadOnlyIntegerProperty getTotalGenres();


}
