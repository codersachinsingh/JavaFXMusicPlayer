package musicplayer.metadata;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Album implements MusicAlbum {
    private ReadOnlyStringWrapper albumTitle;
    private ObservableList<MusicFile> songs;
    private ReadOnlyIntegerWrapper totalSongs;
    private ReadOnlyStringWrapper albumReleaseYear;
    private ReadOnlyObjectWrapper<AlbumArtist> albumArtist;
    private ReadOnlyObjectWrapper<Image> albumArtwork;

    public Album(String albumTitle, String albumReleaseYear,AlbumArtist albumArtist, Image albumArtwork) {
        this.albumTitle = new ReadOnlyStringWrapper(this,"album title",albumTitle);
        this.albumReleaseYear = new ReadOnlyStringWrapper(this,"album release year",albumReleaseYear);
        this.albumArtwork = new ReadOnlyObjectWrapper<>(this,"artwork",albumArtwork);
        this.totalSongs = new ReadOnlyIntegerWrapper(this,"total songs",0);
        this.albumArtist = new ReadOnlyObjectWrapper<>(this,"album artist",albumArtist);
        this.songs = FXCollections.observableArrayList();
        this.songs.addListener(this::invalidated);
    }



    @Override
    public ReadOnlyStringProperty albumNameProperty() {
        return albumTitle.getReadOnlyProperty();
    }

    @Override
    public String getAlbumName() {
        return albumTitle.getValue();
    }

    @Override
    public ObservableList<MusicFile> getSongs() {
        return songs;
    }

    @Override
    public ReadOnlyObjectProperty<AlbumArtist> albumArtistProperty() {
        return albumArtist.getReadOnlyProperty();
    }

    @Override
    public AlbumArtist getAlbumArtist() {
        return albumArtist.getValue();
    }


    @Override
    public ReadOnlyIntegerProperty totalSongsProperty() {
        return totalSongs.getReadOnlyProperty();
    }

    @Override
    public int getTotalSongs() {
        return totalSongs.get();
    }

    @Override
    public ReadOnlyStringProperty albumReleaseYearProperty() {
        return albumReleaseYear.getReadOnlyProperty();
    }

    @Override
    public String getAlbumReleaseYear() {
        return albumReleaseYear.get();
    }

    @Override
    public ReadOnlyObjectProperty<Image> albumArtworkProperty() {
        return albumArtwork.getReadOnlyProperty();
    }

    @Override
    public Image getAlbumArtwork() {
        return albumArtwork.getValue();
    }

    private void invalidated(Observable observable) {
        totalSongs.set(songs.size());
    }

    @Override
    public String toString() {
        String str = albumTitle.get() +  "  [ Release Year : " + albumReleaseYear.get() + ", Total Songs : " + totalSongs.get() + "]";
        return str;
    }
}
