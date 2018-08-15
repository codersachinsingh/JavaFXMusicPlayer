package musicplayer.metadata;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Artist implements MusicArtist {
    private ReadOnlyStringWrapper artistName;
    private ObservableList<MusicAlbum> albums = FXCollections.observableArrayList();
    private ReadOnlyIntegerWrapper totalAlbums;

    public Artist(String artistName) {
        this.artistName = new ReadOnlyStringWrapper(this,"artist",artistName);
        albums.addListener(this::invalidated);
        totalAlbums = new ReadOnlyIntegerWrapper(this,"total albums",0);
    }

    @Override
    public ReadOnlyStringProperty artistNameProperty() {
        return artistName.getReadOnlyProperty();
    }

    @Override
    public String getArtistName() {
        return artistName.getValue();
    }

    @Override
    public ObservableList<MusicAlbum> getAllAlbums() {
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

    private void invalidated(Observable litserner) {
        totalAlbums.set(albums.size());
    }


}
