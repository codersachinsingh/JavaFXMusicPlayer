package musicplayer.media;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	@Override
	public ObservableList<MusicFile> getAllSongs() {
		ObservableList<MusicFile> allsongs = FXCollections.observableArrayList();
		for (String key : songs.keySet())
				allsongs.add(songs.get(key));
		return allsongs.sorted();
	}

	@Override
	public ObservableList<MusicAlbum> getAllAlbums() {
		ObservableList<MusicAlbum> allAlbums = FXCollections.observableArrayList();
		for (String key : albums.keySet())
				allAlbums.add(albums.get(key));
		return allAlbums.sorted();
	}

	@Override
	public ObservableList<MusicArtist> getAllArtists() {
		ObservableList<MusicArtist> allArtists = FXCollections.observableArrayList();
		for (String key : artists.keySet())
				allArtists.add(artists.get(key));
		return allArtists.sorted();
	}

	@Override
	public ObservableList<MusicGenre> getAllGenres() {
		ObservableList<MusicGenre> allgenre = FXCollections.observableArrayList();
		for (String key : genres.keySet())
				allgenre.add(genres.get(key));
		return allgenre.sorted();
	}
}
