package musicplayer.media;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import musicplayer.metadata.*;

public class MediaLibrary implements Library {
    private ObservableMap<String, MusicFile> songs = FXCollections.observableHashMap();
    private ObservableMap<String, MusicAlbum> albums = FXCollections.observableHashMap();
    private ObservableMap<String, MusicArtist> artists = FXCollections.observableHashMap();
    private ObservableMap<String, MusicGenre> genres = FXCollections.observableHashMap();

    private ReadOnlyIntegerWrapper totalSongs = new ReadOnlyIntegerWrapper(0);
    private ReadOnlyIntegerWrapper totalAlbums = new ReadOnlyIntegerWrapper(0);
    private ReadOnlyIntegerWrapper totalArtists = new ReadOnlyIntegerWrapper(0);
    private ReadOnlyIntegerWrapper totalGenres = new ReadOnlyIntegerWrapper(0);

    public MediaLibrary() {
        songs.addListener((Observable change) -> totalSongs.set(songs.size()));
        albums.addListener((Observable change) -> totalAlbums.set(albums.size()));
        artists.addListener((Observable change) -> totalArtists.set(artists.size()));
        genres.addListener((Observable change) -> totalGenres.set(genres.size()));




    }

    public ObservableMap<String, MusicFile> getSongs() {
        return songs;
    }

    public ObservableMap<String, MusicAlbum> getAlbums() {
        return albums;
    }

    public ObservableMap<String, MusicArtist> getArtists() {
        return artists;
    }

    public ObservableMap<String, MusicGenre> getGenres() {
        return genres;
    }

    @Override
    public ReadOnlyIntegerProperty getTotalSongs() {
        return totalSongs.getReadOnlyProperty();
    }

    @Override
    public ReadOnlyIntegerProperty getTotalAlbums() {
        return totalAlbums.getReadOnlyProperty();
    }

    @Override
    public ReadOnlyIntegerProperty getTotalArtists() {
        return totalArtists.getReadOnlyProperty();
    }

    @Override
    public ReadOnlyIntegerProperty getTotalGenres() {
        return totalGenres.getReadOnlyProperty();
    }
}
