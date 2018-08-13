package musicplayer.metadata;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;

public class AlbumArtist implements MusicAlbumArtist{
    private ReadOnlyStringWrapper albumArtistName;
    private ObservableList<Album> albums;
    private ReadOnlyIntegerWrapper totalAlbums;
    @Override
    public ReadOnlyStringProperty albumArtistNameProperty() {
        return albumArtistName.getReadOnlyProperty();
    }

    @Override
    public String getAlbumArtistName() {
        return albumArtistName.getValue();
    }

    @Override
    public ObservableList<Album> getAlbums() {
        return albums;
    }

    @Override
    public ReadOnlyIntegerProperty totalAlbumsProperty() {
        return totalAlbums.getReadOnlyProperty();
    }

    @Override
    public int getTotalAlbums() {
        return totalAlbums.get();
    }
}
