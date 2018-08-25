package musicplayer.metadata;

import java.util.List;

import javafx.scene.image.Image;

public interface MusicAlbum {
	List<MusicFile> getSongs();
	String getAlbumTitle();
	String getArtworkLocation();
	Image getAlbumArtwork();
	Image getAlbumArtwork(int width , int height);
	String getReleaseYear();
	String getAlbumArtist();
	int getTotalSongs();
}
