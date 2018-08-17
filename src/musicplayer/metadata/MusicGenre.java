package musicplayer.metadata;

import java.util.List;

public interface MusicGenre {
    String getGenreTitle();
    List<MusicFile> getSongs();
}
