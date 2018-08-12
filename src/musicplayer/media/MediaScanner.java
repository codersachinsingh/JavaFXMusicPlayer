package musicplayer.media;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableSet;

public interface MediaScanner {
	ObservableSet<String> allowedExtensionsProperty();
	void addAllowedExtension(String extension);
	void removeAllowedExtention(String extension);
	Iterator<String> getAllowedExtensions();
	MediaLocator getMediaLocator();
}
