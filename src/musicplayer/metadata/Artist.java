package musicplayer.metadata;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;

public class Artist implements MusicArtist {
    private ReadOnlyStringWrapper artistName;
    private ObservableList<Album> albums;
    private ReadOnlyIntegerWrapper totalAlbums;
    @Override
    public ReadOnlyStringProperty artistNameProperty() {
        return artistName.getReadOnlyProperty();
    }

    @Override
    public String getArtistName() {
        return artistName.getValue();
    }

    @Override
    public ObservableList<Album> getAllAlbums() {
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
