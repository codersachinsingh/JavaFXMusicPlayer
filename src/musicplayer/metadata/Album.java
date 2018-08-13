package musicplayer.metadata;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Album implements MusicAlbum {
    private ReadOnlyStringWrapper albumTitle;
    private ObservableList<Song> songs;
    private ReadOnlyIntegerWrapper totalSongs;
    private ReadOnlyIntegerWrapper albumReleaseYear;
    private ReadOnlyObjectWrapper<AlbumArtist> albumArtist;
    private ReadOnlyObjectWrapper<Image> albumArtwork;

    @Override
    public ReadOnlyStringProperty albumNameProperty() {
        return albumTitle.getReadOnlyProperty();
    }

    @Override
    public String getAlbumName() {
        return albumTitle.getValue();
    }

    @Override
    public ObservableList<Song> getSongs() {
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
    public ReadOnlyIntegerProperty totoalSongsProperty() {
        return totalSongs.getReadOnlyProperty();
    }

    @Override
    public int getTotalSongs() {
        return totalSongs.get();
    }

    @Override
    public ReadOnlyIntegerProperty albumReleaseYearProperty() {
        return albumReleaseYear.getReadOnlyProperty();
    }

    @Override
    public int getAlbumReleaseYear() {
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
}
