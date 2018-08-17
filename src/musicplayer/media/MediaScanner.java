package musicplayer.media;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;

public interface MediaScanner {
	ObservableSet<String> allowedExtensionsProperty();
	ObservableMap<String,List<String>> getMediaFiles();
	void addAllowedExtension(String extension);
	void removeAllowedExtention(String extension);
	List<String> getAllMediaFiles();
	Iterator<String> getAllowedExtensions();
	MediaLocator getMediaLocator();
}
