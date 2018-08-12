package musicplayer.media;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

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

	private final String APPLICATION_NAME = "JavaFXMusicPlayer";
	private final String SAVE_FILE_LOCATION;
	private final String SAVE_FILENAME = "MediaSearchLocations";

	private ObservableSet<String> locationsSet;

	/**
	 * 
	 * */
	private MediaScanLocations() {
		SAVE_FILE_LOCATION = getSaveFilePath();
		Set<String> hibernatedSet = retriveState();
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
	 * creates application data directory in the file system.
	 */
	private String getSaveFilePath() {
		/* Get the Application Data Directory from the System. */
		String appDataDir = System.getenv("LOCALAPPDATA");

		/* Create JavaFXMusicPlayer own Directory. */
		File applicationDir = new File(appDataDir + "/" + APPLICATION_NAME);

		if (!applicationDir.exists()) {
			applicationDir.mkdirs();
		}

		/* Create file for storing media location info. */
		File saveFile = new File(applicationDir, SAVE_FILENAME);

		if (!saveFile.exists()) {
			// Just create the file.
			try (FileOutputStream fos = new FileOutputStream(saveFile)) {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return saveFile.getAbsolutePath();
	}

	/**
	 * Retrives set from the disk file
	 */
	private Set<String> retriveState() {
		Set<String> set = null;
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(new File(SAVE_FILE_LOCATION))))) {
			set = (Set<String>) ois.readObject();
		} catch (EOFException e) {
			System.err.println("Disk File has No Data Available");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class Not Found");
		}

		return set;
	}

	/**
	 * Stores the current set state to a disk file.
	 */
	private void saveState() {
		// get the iterator from observable set
		Iterator<String> iterator = locationsSet.iterator();
		Set<String> set = new TreeSet<>();
		while (iterator.hasNext()) {
			set.add(iterator.next());
		}

		// now serialize the set

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(new File(SAVE_FILE_LOCATION))))) {
			oos.writeObject(set);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * save the changes made to the set to external disk file.
	 */
	private void invalidated(Observable value) {
		saveState();
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
