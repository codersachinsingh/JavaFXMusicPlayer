package musicplayer.metadata;

import javafx.beans.property.*;
import javafx.util.Duration;

public class Song implements MusicFile {
    private ReadOnlyStringWrapper title;
    private ObjectProperty<MusicAlbum> album;
    private ObjectProperty<MusicArtist> artist;
    private ObjectProperty<MusicGenre> genre;
    private ReadOnlyObjectWrapper<Duration> duration;
    private ReadOnlyStringWrapper bitrate;
    private ReadOnlyStringWrapper sampleRate;
    private ReadOnlyStringWrapper fileLocation;
    private BooleanProperty favorite;

    @Override
    public String toString() {
        String str = "Title : " + title.get() + "\n" +
                "Album : " + album.get() + "\n" +
                "Artist : " + artist.get() + "\n" +
                "Genre : " + genre.get() + "\n" +
                "Bitrate : " + bitrate.get() + "\n" +
                "Sample Rate : " + sampleRate.get() + "\n" +
                "Location : " + fileLocation.get() + "\n";
        return str;
    }

    public Song(String title, Duration duration, String bitrate, String sampleRate, String fileLocation) {
        this.title = new ReadOnlyStringWrapper(this,"title",title);
        this.duration = new ReadOnlyObjectWrapper<>(this,"duration",duration);
        this.bitrate = new ReadOnlyStringWrapper(this,"bitrate",bitrate);
        this.sampleRate = new ReadOnlyStringWrapper(this,"sample rate",sampleRate);
        this.fileLocation = new ReadOnlyStringWrapper(this,"file location",fileLocation);
        this.favorite = new SimpleBooleanProperty(false,"favorite",false);
        this.album = new SimpleObjectProperty<>(this,"album",null);
        this.artist = new SimpleObjectProperty<>(this,"artist",null);
        this.genre = new SimpleObjectProperty<>(this,"genre",null);
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
    public ObjectProperty<MusicAlbum> albumProperty() {
        return album;
    }

    @Override
    public MusicAlbum getAlbum() {
        return album.getValue();
    }

    @Override
    public void setAlbum(MusicAlbum album) {
        albumProperty().setValue(album);
    }
    @Override
    public ObjectProperty<MusicArtist> artistProperty() {
        return artist;
    }

    @Override
    public MusicArtist getArtist() {
        return artist.getValue();
    }
    @Override
    public void setArtist(MusicArtist artist) {
        artistProperty().setValue(artist);
    }
    @Override
    public ObjectProperty<MusicGenre> genreProperty() {
        return genre;
    }

    @Override
    public MusicGenre getGenre() {
        return genre.getValue();
    }
    @Override
    public void setGenre(MusicGenre genre) {
        genreProperty().setValue(genre);
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
    public ReadOnlyStringProperty bitrateProperty() {
        return bitrate.getReadOnlyProperty();
    }

    @Override
    public String getBitrate() {
        return bitrate.get();
    }

    @Override
    public ReadOnlyStringProperty sampleRateProperty() {
        return sampleRate.getReadOnlyProperty();
    }

    @Override
    public String getSampleRate() {
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
