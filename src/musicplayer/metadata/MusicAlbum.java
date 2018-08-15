package musicplayer.metadata;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public interface MusicAlbum {
    ReadOnlyStringProperty albumNameProperty();
    String getAlbumName();
    ObservableList<Song> getSongs();
    ReadOnlyObjectProperty<AlbumArtist> albumArtistProperty();
    AlbumArtist getAlbumArtist();
    ReadOnlyIntegerProperty totalSongsProperty();
    int getTotalSongs();
    ReadOnlyStringProperty albumReleaseYearProperty();
    String getAlbumReleaseYear();
    ReadOnlyObjectProperty<Image> albumArtworkProperty();
    Image getAlbumArtwork();
}
