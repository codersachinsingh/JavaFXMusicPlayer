package musicplayer.ui.fxmls;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import musicplayer.metadata.MusicFile;

public class SongsListController {
	@FXML
	private ListView<MusicFile> songList;
	
	public void initialize() {
		
	}

	public ListView<MusicFile> getSongList() {
		return songList;
	}
}
