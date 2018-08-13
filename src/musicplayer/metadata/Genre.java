package musicplayer.metadata;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;

public class Genre implements MusicGenre {
    private ReadOnlyStringWrapper genreTitle;
    private ObservableList<Song> songs;
    private ReadOnlyIntegerWrapper totalSongs;
    @Override
    public ReadOnlyStringProperty genreTitleProperty() {
        return genreTitle.getReadOnlyProperty();
    }

    @Override
    public String getGenreTitle() {
        return genreTitle.getValue();
    }

    @Override
    public ObservableList<Song> getSongs() {
        return songs;
    }

    @Override
    public ReadOnlyIntegerProperty totalSongsProperty() {
        return totalSongs.getReadOnlyProperty();
    }

    @Override
    public int getTotalSongs() {
        return totalSongs.get();
    }
}
