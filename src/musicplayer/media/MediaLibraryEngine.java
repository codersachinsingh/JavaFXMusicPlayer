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
import org.jaudiotagger.tag.id3.ID3v22Tag;
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
        try {
            File file = new File(filepath);
            MP3File mp3File = new MP3File(file);
            /* Library Items*/
            MusicFile song;
            MusicAlbum album;
            MusicArtist artist;
            MusicGenre genre;
            /* MetaInfo */
            /* Set Default Value */
            String title = file.getName();
            String albumTitle = "Unknown Album";
            String artistName = "Unknown Artist";
            String albumArtistName = "Unknown Album Artist";
            String genreTitle = "Unknown Genre";
            String releaseYear = "----";
            String sampleRate  = "Unknown Sample Rate";
            String bitRate = "Unknown Bit Rate";
            Duration duration = Duration.UNKNOWN;
            Image artwork = AppUtils.DEFAULT_ALBUM_ARTWORK;

            if (mp3File.hasID3v2Tag()) {
                AbstractID3v2Tag metaTag = mp3File.getID3v2Tag();
                List<String> info;
                info = metaTag.getAll(FieldKey.TITLE);
                if (!info.isEmpty())
                    title = info.get(0);
                info = metaTag.getAll(FieldKey.ALBUM);
                if (!info.isEmpty())
                    albumTitle = info.get(0);
                info = metaTag.getAll(FieldKey.ARTIST);
                if (!info.isEmpty())
                    artistName = info.get(0);
                info = metaTag.getAll(FieldKey.ALBUM_ARTIST);
                if (!info.isEmpty())
                    albumArtistName = info.get(0);
                info = metaTag.getAll(FieldKey.YEAR);
                if (!info.isEmpty())
                    releaseYear = info.get(0);
                info = metaTag.getAll(FieldKey.GENRE);
                if (!info.isEmpty())
                    genreTitle = info.get(0);
                Artwork artwk = metaTag.getFirstArtwork();
                if (artwk != null && artwk.getBinaryData() != null)
                    artwork = new Image(new ByteArrayInputStream(artwk.getBinaryData()));

                MP3AudioHeader audioHeader = mp3File.getMP3AudioHeader();
                if (audioHeader!=null) {
                    bitRate = audioHeader.getBitRate() + "Kbps";
                    sampleRate = (double)audioHeader.getSampleRateAsNumber()/1000 + "Khz";
                    duration = Duration.seconds(audioHeader.getTrackLength());
                }

            }

            song = new Song(title,duration,bitRate,sampleRate,filepath);

            if (mediaLibrary.getAlbums().containsKey(albumTitle)) {
                album = mediaLibrary.getAlbums().get(albumTitle);
                album.getSongs().add(song);
                song.setAlbum(album);
            }
            else {
                AlbumArtist albumArtist = new AlbumArtist(artistName);
                album = new Album(albumTitle,releaseYear,albumArtist,artwork);
                album.getSongs().add(song);
                song.setAlbum(album);
                mediaLibrary.getAlbums().put(albumTitle,album);
            }

            if (mediaLibrary.getArtists().containsKey(artistName)) {
                artist = mediaLibrary.getArtists().get(artistName);
                artist.getAllAlbums().add(album);
                song.setArtist(artist);
            }
            else {
                artist = new Artist(artistName);
                artist.getAllAlbums().add(album);
                song.setArtist(artist);
                mediaLibrary.getArtists().put(artistName,artist);
            }

            if (mediaLibrary.getGenres().containsKey(genreTitle)) {
                genre = mediaLibrary.getGenres().get(genreTitle);
                genre.getSongs().add(song);
                song.setGenre(genre);
            }
            else {
                genre = new Genre(genreTitle);
                genre.getSongs().add(song);
                song.setGenre(genre);
                mediaLibrary.getGenres().put(genreTitle,genre);
            }
            mediaLibrary.getSongs().put(filepath,song);
        }
        catch(IOException  | TagException | ReadOnlyFileException | CannotReadException | InvalidAudioFrameException io) {
            System.err.println("Error while reading metadata from file. target file : " + filepath);
        }
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
