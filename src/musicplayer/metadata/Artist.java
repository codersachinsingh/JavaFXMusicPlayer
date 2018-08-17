package musicplayer.metadata;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Artist implements MusicArtist {
	private List<MusicAlbum> albums;
	private String artistName;
	
	public Artist(String artist) {
		this.artistName = artist;
	}
	@Override
	public String getArtistName() {
		return artistName;
	}

	@Override
	public List<MusicAlbum> getAlbums() {
		if (albums == null)
			albums = new ArrayList<>();
		return albums;
	}

	@Override
	public int getTotalAlbums() {
		if (albums != null)
			return albums.size();
		else
			return 0;
	}
    
	@Override
	public String toString() {
		return "Artist : " + artistName;
	}

}
