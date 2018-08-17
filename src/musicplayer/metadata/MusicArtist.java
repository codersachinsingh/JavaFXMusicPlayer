package musicplayer.metadata;

import java.util.List;

public interface MusicArtist {
   String getArtistName();
   List<MusicAlbum> getAlbums();
   int getTotalAlbums();
}
