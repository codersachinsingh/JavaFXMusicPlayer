package musicplayer.metadata;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.collections.ObservableList;

public interface MusicArtist {
    ReadOnlyStringProperty artistNameProperty();
    String getArtistName();
    ObservableList<MusicAlbum> getAllAlbums();
    ReadOnlyIntegerProperty totalAlbumsProperty();
    int getTotalAlbums();
}
