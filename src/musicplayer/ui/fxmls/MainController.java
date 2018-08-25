package musicplayer.ui.fxmls;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import musicplayer.media.MediaFileScanner;
import musicplayer.media.MediaLibraryEngine;

public class MainController {
	@FXML
	private BorderPane root;
	@FXML
	private AnchorPane bottomBar;
	@FXML
	private NowPlayingBarController bottomBarController;
	@FXML
	private AnchorPane topBar;
	@FXML
	private TopNavigationBarController topBarController;
	@FXML
	private AnchorPane centerScreens;
	@FXML
	private CenterScreensController centerScreensController;
	private MediaFileScanner mediaFileScanner;
	private MediaLibraryEngine mediaLibraryEngine;
	private AnchorPane songsScreen;
	private SongsListController songsController;
	private AnchorPane nowPlayingScreen;
	private NowPlayingScreenController nowPlayingScreenController;
	private AnchorPane topNavigationbar;
	private TopNavigationBarController topNavigationBarController;
	private AnchorPane nowPlayingBar;
	private NowPlayingBarController nowPlayingBarController;
	private AnchorPane songsList;
	private SongsListController songsListController;
	private AnchorPane albumsList;
	private AlbumsListController albumsListController;
	private AnchorPane artistsList;
	private ArtistsListController artistsListController;
	private AnchorPane genresList;
	private GenresListController genresListController;
	private AnchorPane playListScreen;
	private PlayListScreenController playListScreenController;

	public void initialize() {
		System.out.println("MainController");
		FXMLLoader loader = null;
		try {
			// Load Top Navigation Bar
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/TopNavigationBar.fxml"));
			topNavigationbar = loader.load();
			topNavigationBarController = loader.getController();

			// Load Now Playing Bar
			loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/NowPlayingBar.fxml"));
			nowPlayingBar = loader.load();
			nowPlayingBarController = loader.getController();

			// Load Now Playing Screen

			loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/NowPlayingScreen.fxml"));
			nowPlayingScreen = loader.load();
			nowPlayingScreenController = loader.getController();

			// Load SongsList Screen
			loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/SongsList.fxml"));
			songsList = loader.load();
			songsListController = loader.getController();

			// Load AlbumsList Screen
			loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/AlbumsList.fxml"));
			albumsList = loader.load();
			albumsListController = loader.getController();

			// Load ArtistsList Screen
			loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/ArtistsList.fxml"));
			artistsList = loader.load();
			artistsListController = loader.getController();

			// Load GenresList Screen
			loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/GenresList.fxml"));
			genresList = loader.load();
			genresListController = loader.getController();

			// Load PlayList Screen
			loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("musicplayer/ui/fxmls/Playlists.fxml"));
			playListScreen = loader.load();
			playListScreenController = loader.getController();

			centerScreens.getChildren().add(nowPlayingScreen);
			AnchorPane.setTopAnchor(nowPlayingScreen, 0.0);
			AnchorPane.setBottomAnchor(nowPlayingScreen, 0.0);
			AnchorPane.setRightAnchor(nowPlayingScreen, 0.0);
			AnchorPane.setLeftAnchor(nowPlayingScreen, 0.0);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root.setBottom(null);
	}
}
