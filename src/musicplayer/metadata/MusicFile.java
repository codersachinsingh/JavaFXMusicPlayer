package musicplayer.metadata;

import javafx.beans.property.*;
import javafx.util.Duration;

public interface MusicFile {
    ReadOnlyStringProperty titleProperty();
    String getTitle();
    ObjectProperty<MusicAlbum> albumProperty();
    MusicAlbum getAlbum();
    void setAlbum(MusicAlbum album);
    ObjectProperty<MusicArtist> artistProperty();
    MusicArtist getArtist();
    void setArtist(MusicArtist artist);
    ObjectProperty<MusicGenre> genreProperty();
    MusicGenre getGenre();
    void setGenre(MusicGenre genre);
    ReadOnlyObjectProperty<Duration> durationProperty();
    Duration getDuration();
    ReadOnlyStringProperty bitrateProperty();
    String getBitrate();
    ReadOnlyStringProperty sampleRateProperty();
    String getSampleRate();
    ReadOnlyStringProperty fileLocationProperty();
    String getFileLocation();
    BooleanProperty favoriteProperty();
    boolean isFavorite();
    void setFavorite(boolean favorite);
}
