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
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
            double duration = 0.0;
            String artwork = AppUtils.DEFAULT_ALBUM_ARTWORK_LOCATION;

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
                if (artwk != null && artwk.getBinaryData() != null && !albumTitle.equals("Unknown Album"))
                    artwork = storeArtworkinDisk(artwk.getBinaryData(), albumTitle);

                MP3AudioHeader audioHeader = mp3File.getMP3AudioHeader();
                if (audioHeader!=null) {
                    bitRate = audioHeader.getBitRate() + "Kbps";
                    sampleRate = (double)audioHeader.getSampleRateAsNumber()/1000 + "Khz";
                    duration = audioHeader.getTrackLength();
                }

            }

            song = new Song(title,bitRate,sampleRate,filepath,duration);

            if (mediaLibrary.getAlbums().containsKey(albumTitle)) {
                album = mediaLibrary.getAlbums().get(albumTitle);
                album.getSongs().add(song);
                song.setAlbum(album);
            }
            else {
                album = new Album(albumTitle,releaseYear,albumArtistName,artwork);
                album.getSongs().add(song);
                song.setAlbum(album);
                mediaLibrary.getAlbums().put(albumTitle,album);
            }

            if (mediaLibrary.getArtists().containsKey(artistName)) {
                artist = mediaLibrary.getArtists().get(artistName);
                artist.getAlbums().add(album);
                song.setArtist(artist);
            }
            else {
                artist = new Artist(artistName);
                artist.getAlbums().add(album);
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

    private String storeArtworkinDisk(byte[] binarydata , String albumName) {
    	File artworkDir = AppUtils.AlbumArtworkFolder();
    	File albumArtworkFile = new File(artworkDir,albumName + ".jpg");
    	if (!albumArtworkFile.exists()) {
    		try (FileOutputStream fos = new FileOutputStream(albumArtworkFile))
    		{
				fos.write(binarydata);
				System.out.println("Album Artwork Created for " + albumName);
			} catch (IOException e) {
				System.err.println("Can't create albumart for " + albumName);
				e.printStackTrace();
			}
    	}
    	return albumArtworkFile.getAbsolutePath();
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
    	System.out.println("Size : "+mediaFileScanner.getMediaFiles().size());
        for (String key : mediaFileScanner.getMediaFiles().keySet()) {
            List<String> files = mediaFileScanner.getMediaFiles().get(key);
            for (int i = 0 ; i < files.size();i++) {
                processFile(files.get(i));
                System.out.println("Processing : " + i);
            }
        }
    }

}
