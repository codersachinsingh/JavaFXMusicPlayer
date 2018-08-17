package musicplayer.metadata;

public interface MusicFile {
    String getTitle();
    String getBitrate();
    String getSampleRate();
    String getFileLocation();
    MusicAlbum getAlbum();
    void setAlbum(MusicAlbum musicAlbum);
    MusicArtist getArtist();
    void setArtist(MusicArtist artist);
    MusicGenre getGenre();
    void setGenre(MusicGenre genre);
    double getDuration();
    boolean isFavorite();
    void setFavorit(boolean f);
}
