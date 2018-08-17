package musicplayer.metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.embed.swing.Disposer;

import javafx.scene.image.Image;
import musicplayer.util.AppUtils;

public class Album implements MusicAlbum {
	private String title;
	private List<MusicFile> songs;
	private String releaseYear;
	private String albumArtist;
	private String artworkLocation;
	private Image artwork;
	public Album(String title ,String releaseYear, String albumArtist,String artwork) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.albumArtist = albumArtist;
		this.artworkLocation = artwork;
	}

	@Override
	public List<MusicFile> getSongs() {
		if (songs == null)
			return new ArrayList<>();
		return songs;
	}

	@Override
	public String getArtworkLocation() {
		return artworkLocation;
	}

	@Override
	public String getReleaseYear() {
		return releaseYear;
	}

	@Override
	public String getAlbumArtist() {
		return albumArtist;
	}

	@Override
	public int getTotalSongs() {
		if (songs != null)
			return songs.size();
		else
			return 0;
	}

	@Override
	public String getAlbumTitle() {
		return title;
	}
	@Override
	public Image getAlbumArtwork() {
		if (artwork == null) {
			File artworkfile = new File(AppUtils.AlbumArtworkFolder(),title + ".jpg");
			if (artworkfile.exists()) {
				try {
					artwork = new Image(new FileInputStream(artworkfile));
				} catch (FileNotFoundException e) {
					artwork = AppUtils.DEFAULT_ALBUM_ARTWORK;
				}
				return artwork;
			}
		}
		return artwork;
	}
	
	public void disposeAlbumArtwork() {
		artwork = null;
	}
	@Override
	public String toString() {
		return title + "[Release Date : " + releaseYear + " , Album Artist : " + albumArtist + "]";
	}
	
}