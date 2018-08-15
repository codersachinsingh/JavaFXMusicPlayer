package musicplayer.metadata;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.collections.ObservableList;

public interface MusicAlbumArtist {
    ReadOnlyStringProperty albumArtistNameProperty();
    String getAlbumArtistName();
    ObservableList<MusicAlbum> getAlbums();
    ReadOnlyIntegerProperty totalAlbumsProperty();
    int getTotalAlbums();
}
