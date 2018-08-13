package musicplayer.metadata;

import javafx.beans.property.*;
import javafx.util.Duration;

public class Song implements MusicFile {
    private ReadOnlyStringWrapper title;
    private ReadOnlyObjectWrapper<Album> album;
    private ReadOnlyObjectWrapper<Artist> artist;
    private ReadOnlyObjectWrapper<Genre> genre;
    private ReadOnlyObjectWrapper<Duration> duration;
    private ReadOnlyIntegerWrapper bitrate;
    private ReadOnlyDoubleWrapper sampleRate;
    private ReadOnlyStringWrapper fileLocation;
    private BooleanProperty favorite;
    public Song() {

    }

    @Override
    public ReadOnlyStringProperty titleProperty() {
       return title.getReadOnlyProperty();
    }

    @Override
    public String getTitle() {
        return title.getValue();
    }

    @Override
    public ReadOnlyObjectProperty<Album> albumProperty() {
        return album.getReadOnlyProperty();
    }

    @Override
    public Album getAlbum() {
        return album.getValue();
    }

    @Override
    public ReadOnlyObjectProperty<Artist> artistProperty() {
        return artist.getReadOnlyProperty();
    }

    @Override
    public Artist getArtist() {
        return artist.getValue();
    }

    @Override
    public ReadOnlyObjectProperty<Genre> genreProperty() {
        return genre.getReadOnlyProperty();
    }

    @Override
    public Genre getGenre() {
        return genre.getValue();
    }

    @Override
    public ReadOnlyObjectProperty<Duration> durationProperty() {
        return duration.getReadOnlyProperty();
    }

    @Override
    public Duration getDuration() {
        return duration.getValue();
    }

    @Override
    public ReadOnlyIntegerProperty bitrateProperty() {
        return bitrate.getReadOnlyProperty();
    }

    @Override
    public int getBitrate() {
        return bitrate.get();
    }

    @Override
    public ReadOnlyDoubleProperty sampleRateProperty() {
        return sampleRate.getReadOnlyProperty();
    }

    @Override
    public double getSampleRate() {
        return sampleRate.getValue();
    }

    @Override
    public ReadOnlyStringProperty fileLocationProperty() {
        return fileLocation.getReadOnlyProperty();
    }

    @Override
    public String getFileLocation() {
        return fileLocation.getValue();
    }

    @Override
    public BooleanProperty favoriteProperty() {
        return favorite;
    }

    @Override
    public boolean isFavorite() {
        return favorite.get();
    }

    @Override
    public void setFavorite(boolean favorite) {
        this.favorite.set(favorite);
    }


}
