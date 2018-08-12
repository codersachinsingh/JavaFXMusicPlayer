package musicplayer.media;

import java.io.Serializable;
import javafx.collections.ObservableSet;
/**
 * Provides a set of file system directories location (<i>as string</i>) where media can be found;
 * @author Sachin Singh
 */
public interface MediaLocator {
    ObservableSet<String> getMediaScanLocations();
    int getTotalMediaLocations();
}
