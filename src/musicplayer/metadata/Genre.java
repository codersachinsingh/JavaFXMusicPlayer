package musicplayer.metadata;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Genre implements MusicGenre {
    private ReadOnlyStringWrapper genreTitle;
    private ObservableList<MusicFile> songs = FXCollections.observableArrayList();
    private ReadOnlyIntegerWrapper totalSongs;

    public Genre(String title) {
        genreTitle = new ReadOnlyStringWrapper(this,"genre",title);
        this.totalSongs = new ReadOnlyIntegerWrapper(this,"total songs",0);
    }

    @Override
    public ReadOnlyStringProperty genreTitleProperty() {
        return genreTitle.getReadOnlyProperty();
    }

    @Override
    public String getGenreTitle() {
        return genreTitle.getValue();
    }

    @Override
    public ObservableList<MusicFile> getSongs() {
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
