package musicplayer.metadata;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Genre implements MusicGenre {
	private String genreTitle;
	private List<MusicFile> songs;
	
	public Genre(String title) {
		this.genreTitle = title;
	}
	
	@Override
	public String getGenreTitle() {
		return genreTitle;
	}

	@Override
	public List<MusicFile> getSongs() {
		if (songs == null)
			songs = new ArrayList<>();
		return songs;
	}

	@Override
	public String toString() {
		return "Genre : " + genreTitle;
	}   
}
