package musicplayer.metadata;

import javafx.beans.property.*;
import javafx.util.Duration;

public interface MusicFile {
    ReadOnlyStringProperty titleProperty();
    String getTitle();
    ReadOnlyObjectProperty<Album> albumProperty();
    Album getAlbum();
    ReadOnlyObjectProperty<Artist> artistProperty();
    Artist getArtist();
    ReadOnlyObjectProperty<Genre> genreProperty();
    Genre getGenre();
    ReadOnlyObjectProperty<Duration> durationProperty();
    Duration getDuration();
    ReadOnlyIntegerProperty bitrateProperty();
    int getBitrate();
    ReadOnlyDoubleProperty sampleRateProperty();
    double getSampleRate();
    ReadOnlyStringProperty fileLocationProperty();
    String getFileLocation();
    BooleanProperty favoriteProperty();
    boolean isFavorite();
    void setFavorite(boolean favorite);
}
