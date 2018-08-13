package musicplayer.metadata;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public interface MusicAlbum {
    ReadOnlyStringProperty albumNameProperty();
    String getAlbumName();
    ObservableList<Song> getSongs();
    ReadOnlyObjectProperty<AlbumArtist> albumArtistProperty();
    AlbumArtist getAlbumArtist();
    ReadOnlyIntegerProperty totoalSongsProperty();
    int getTotalSongs();
    ReadOnlyIntegerProperty albumReleaseYearProperty();
    int getAlbumReleaseYear();
    ReadOnlyObjectProperty<Image> albumArtworkProperty();
    Image getAlbumArtwork();
}
