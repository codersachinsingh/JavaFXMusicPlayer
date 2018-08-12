package musicplayer.media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import musicplayer.util.AppUtils;
import musicplayer.util.DataCollectionSerializer;

/**
 * This class is an implementation of Media Locator interface. It manages a set
 * of strings which corresponds to a file system directory where media can be
 * found. It serializes the set. so that it can be retrieved after closing the
 * application.
 * 
 * @see MediaLocator
 * @author Sachin Singh
 * @version 1.0
 */
public class MediaScanLocations implements MediaLocator {
	private static MediaScanLocations instance;

	private DataCollectionSerializer<Set<String>> dataCollectionSerializer;
	private ObservableSet<String> locationsSet;

	/**
	 * 
	 * */
	private MediaScanLocations() {
		dataCollectionSerializer = new DataCollectionSerializer<>(AppUtils.SCAN_LOCATION_FILE);
		Set<String> hibernatedSet = dataCollectionSerializer.retrieveState();
		if (hibernatedSet != null)
			locationsSet = FXCollections.observableSet(hibernatedSet);
		else
			locationsSet = FXCollections.observableSet();

		locationsSet.addListener(this::invalidated);

	}

	@Override
	public ObservableSet<String> getMediaScanLocations() {
		return locationsSet;
	}


	/**
	 * save the changes made to the set to external disk file.
	 */
	private void invalidated(Observable value) {
		Iterator<String> iterator = locationsSet.iterator();
		Set<String> set = new TreeSet<>();
		while (iterator.hasNext()) {
			set.add(iterator.next());
		}
		dataCollectionSerializer.saveState(set);
	}

	/**
	 * returns the total media locations stored in the set.
	 */
	@Override
	public int getTotalMediaLocations() {
		return locationsSet.size();
	}

	public static MediaScanLocations getInstance() {
		if (instance == null)
			instance = new MediaScanLocations();
		return instance;
	}
}
