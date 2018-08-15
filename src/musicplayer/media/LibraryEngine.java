package musicplayer.media;

public interface LibraryEngine {
    Library getMediaLibrary();
    MediaScanner getMediaScanner();
    MediaLocator getMediaLocator();
    void refreshLibrary();
}