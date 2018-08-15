package musicplayer.media;

import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.util.Duration;
import musicplayer.metadata.*;
import musicplayer.util.AppUtils;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.AbstractTag;
import org.jaudiotagger.tag.images.Artwork;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MediaLibraryEngine implements LibraryEngine {
    private MediaLocator mediaLocator;
    private MediaScanner mediaFileScanner;
    private Library mediaLibrary;
    public MediaLibraryEngine(MediaScanner fileScanner , Library library) {
        this.mediaFileScanner = fileScanner;
        this.mediaLocator = mediaFileScanner.getMediaLocator();
        this.mediaLibrary = library;
        refreshLibrary();
        this.mediaFileScanner.getMediaFiles().addListener((MapChangeListener.Change<? extends String , ? extends List<String>> litsener)-> {
            /* Handle the Addition of files */
            if (litsener.wasAdded()) {
                List<String> list = litsener.getValueAdded();
                for (String s : list)
                    processFile(s);
            }

            /*Handle the deletion of files */
            else if (litsener.wasRemoved()){

            }
        });


    }


    private void processFile(String filepath) {
        File file = new File(filepath);
        try {
            MP3File mp3File = new MP3File(file);
            Song song = createSong(mp3File);
            /* *** HANDLE ALBUM **/
            List<String> info = mp3File.getID3v2Tag().getAll(FieldKey.ALBUM);
            if (!info.isEmpty() && info.get(0).trim().length() > 0) {
                String albumName = info.get(0);
                if (mediaLibrary.getAlbums().containsKey(albumName)) {
                    MusicAlbum album = mediaLibrary.getAlbums().get(albumName);
                    album.getSongs().add(song);
                    song.setAlbum(album);
                }
                else {
                    Album album = createAlbum(mp3File);
                    album.getSongs().add(song);
                    mediaLibrary.getAlbums().put(albumName,album);
                    song.setAlbum(album);
                }
            }

          /* *********HANDLE ARTIST************/
            info = mp3File.getID3v2Tag().getAll(FieldKey.ARTIST);
            if (!info.isEmpty() && info.get(0).trim().length() > 0) {
                String artistName = info.get(0);
                if (mediaLibrary.getArtists().containsKey(artistName)) {
                    MusicArtist artist = mediaLibrary.getArtists().get(artistName);
                    artist.getAllAlbums().add(song.getAlbum());
                    song.setArtist(artist);
                }
                else {
                    Artist artist = createArtist(mp3File);
                    artist.getAllAlbums().add(song.getAlbum());
                    song.setArtist(artist);
                    mediaLibrary.getArtists().put(artistName,artist);
                }
            }
            /* **HANDLE GENRE **/
            info  = mp3File.getID3v2Tag().getAll(FieldKey.GENRE);
            if (!info.isEmpty() && info.get(0).trim().length() > 0) {
                String genreTitle = info.get(0);
                if (mediaLibrary.getGenres().containsKey(genreTitle)) {
                    MusicGenre genre = mediaLibrary.getGenres().get(genreTitle);
                    genre.getSongs().add(song);
                    song.setGenre(genre);
                }
                else {
                    Genre genre = createGenre(mp3File);
                    genre.getSongs().add(song);
                    song.setGenre(genre);
                    mediaLibrary.getGenres().put(genreTitle,genre);
                }
            }

             mediaLibrary.getSongs().put(filepath,song);
        } catch (IOException | TagException | ReadOnlyFileException | CannotReadException | InvalidAudioFrameException e) {
            System.err.println("Error Occurred while reading metadata from " + filepath + ", it cant be added to media library");
        }

    }

    private void removeFile(String path) {

    }
    private Song createSong(MP3File mp3File) {
        String title = mp3File.getFile().getName();
        String bitrate = "Unknown Bitrate";
        String samplerate = "Unknown Sample Rate";
        Duration duration = Duration.UNKNOWN;
        String filelocation = mp3File.getFile().getAbsolutePath();
        List<String> info;

        // fetch the title

        if (mp3File.hasID3v2Tag()) {
            info = mp3File.getID3v2Tag().getAll(FieldKey.TITLE);
            if (!info.isEmpty()) {
                title = info.get(0);
            }
        }

        MP3AudioHeader audioHeader = mp3File.getMP3AudioHeader();
        if (audioHeader != null) {
            bitrate = audioHeader.getBitRate() + "Kbps";
            samplerate = (double)audioHeader.getSampleRateAsNumber()/1000 + "Khz";
            duration = Duration.seconds(audioHeader.getTrackLength());
        }
        return new Song(title,duration,bitrate,samplerate,filelocation);
    }

    private Album createAlbum(MP3File mp3File) {
        String albumTitle = "Unknown Album";
        String releaseYear = "0000";
        String albumArtist = "Unknown Album Artist";
        Image artwork = AppUtils.DEFAULT_ALBUM_ARTWORK;

        List<String> info;
        if (mp3File.hasID3v2Tag()) {
            info = mp3File.getID3v2Tag().getAll(FieldKey.ALBUM);
            if (!info.isEmpty())
                albumTitle = info.get(0);

            info = mp3File.getID3v2Tag().getAll(FieldKey.YEAR);
            if (!info.isEmpty())
                releaseYear = info.get(0);

            Artwork a = mp3File.getID3v2Tag().getFirstArtwork();
            if (a!= null && a.getBinaryData()!=null) {
                ByteArrayInputStream stream = new ByteArrayInputStream(a.getBinaryData());
                artwork = new Image(stream);
            }

            info = mp3File.getID3v2Tag().getAll(FieldKey.ALBUM_ARTIST);
            if (!info.isEmpty())
                albumArtist = info.get(0);
        }

        AlbumArtist album_artist = new AlbumArtist(albumArtist);
        Album album = new Album(albumTitle,releaseYear,album_artist,artwork);
        album_artist.getAlbums().add(album);
        return album;
    }

    private Artist createArtist(MP3File mp3File) {
        String artistName = "Unknown Artist";
        if (mp3File.hasID3v2Tag()) {
            List<String> info = mp3File.getID3v2Tag().getAll(FieldKey.ARTIST);
            if (!info.isEmpty())
                artistName = info.get(0);
        }
        return new Artist(artistName);
    }

    private Genre createGenre(MP3File mp3File) {
        String genreTitle = "Unknown Genre";
        if (mp3File.hasID3v2Tag()) {
            List<String> info = mp3File.getID3v2Tag().getAll(FieldKey.GENRE);
            if (!info.isEmpty())
                genreTitle = info.get(0);
        }
        return new Genre(genreTitle);
    }
    @Override
    public Library getMediaLibrary() {
        return mediaLibrary;
    }

    @Override
    public MediaScanner getMediaScanner() {
        return mediaFileScanner;
    }

    @Override
    public MediaLocator getMediaLocator() {
        return mediaLocator;
    }
    @Override
    public void refreshLibrary() {
        for (String key : mediaFileScanner.getMediaFiles().keySet()) {
            List<String> files = mediaFileScanner.getMediaFiles().get(key);
            for (String s : files) {
                processFile(s);
            }
        }
    }

}
