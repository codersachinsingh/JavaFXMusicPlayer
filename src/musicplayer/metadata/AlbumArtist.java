package musicplayer.metadata;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlbumArtist implements MusicAlbumArtist{
    private ReadOnlyStringWrapper albumArtistName = new ReadOnlyStringWrapper(this,"album artist",null);
    private ObservableList<MusicAlbum> albums = FXCollections.observableArrayList();
    private ReadOnlyIntegerWrapper totalAlbums  = new ReadOnlyIntegerWrapper(this,"total albums",0);

    public AlbumArtist(String artistName) {
        albumArtistName.set(artistName);
        albums.addListener(this::invalidated);
    }
    @Override
    public ReadOnlyStringProperty albumArtistNameProperty() {
        return albumArtistName.getReadOnlyProperty();
    }

    @Override
    public String getAlbumArtistName() {
        return albumArtistName.getValue();
    }

    @Override
    public ObservableList<MusicAlbum> getAlbums() {
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

    private void invalidated(Observable observable) {
        totalAlbums.set(albums.size());
    }

}
