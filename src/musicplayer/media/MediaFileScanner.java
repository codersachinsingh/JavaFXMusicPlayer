package musicplayer.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

public class MediaFileScanner implements MediaScanner {
	private ObservableSet<String> allowedExtensions = FXCollections.observableSet("mp3");
	ObservableMap<String, List<String>> mediaFiles = FXCollections.observableHashMap();
	MediaFileExtensionFilter fileExtensionFilter;
	MediaLocator mediaLocator;
	
	public MediaFileScanner(MediaLocator locator) {
		this.mediaLocator = locator;
		String[] strings = new String[allowedExtensions.size()];
		fileExtensionFilter = new MediaFileExtensionFilter(allowedExtensions.toArray(strings));
		startUpScanning();
		mediaLocator.getMediaScanLocations().addListener(this::MediaLocationsChangeHandler);
		allowedExtensions.addListener(this::ExtensionsSetChangeLitsener);
	}
	public ObservableMap<String, List<String>> getmediaFiles() {
		return mediaFiles;
	}
	
	public List<String> getAllMediaFiles() {
		List<String> list = new ArrayList<>();
		Iterator<String> iterator = mediaFiles.keySet().iterator();
		while(iterator.hasNext()) {
			for (String s : mediaFiles.get(iterator.next())) {
				list.add(s);
			}
		}
		return list;
	}
	@Override
	public ObservableSet<String> allowedExtensionsProperty() {
		return allowedExtensions;
	}

	@Override
	public void addAllowedExtension(String extension) {
		allowedExtensions.add(extension);
	}

	@Override
	public Iterator<String> getAllowedExtensions() {
		return allowedExtensions.iterator();
	}

	@Override
	public MediaLocator getMediaLocator() {
		return mediaLocator;
	}
	
	private void startUpScanning() {
		Iterator<String> iterator = mediaLocator.getMediaScanLocations().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			mediaFiles.put(key, scanDirectoryForMedia(key));
		}
	}
	
	private void MediaLocationsChangeHandler(SetChangeListener.Change<? extends String> litsener) {
		if (litsener.wasAdded()) {
			String newAddedLocation = litsener.getElementAdded();
			mediaFiles.put(newAddedLocation, scanDirectoryForMedia(newAddedLocation));
		}
		else if (litsener.wasRemoved()) {
			String removedLocation = litsener.getElementRemoved();
			mediaFiles.remove(removedLocation);
		}
	}
	
	private void ExtensionsSetChangeLitsener(SetChangeListener.Change<? extends String> litsener) {
		if (litsener.wasAdded()) {
			fileExtensionFilter.addExtension(litsener.getElementAdded());
		}
		else {
			fileExtensionFilter.remove(litsener.getElementRemoved());
		}
	}
	
	private List<String> scanDirectoryForMedia(String file){
		List<String> list = new ArrayList<>();
		File f = new File(file);
		List<File> files = new ArrayList<>();
		files = scan(f, files);
		for (File o : files) {
			list.add(o.getAbsolutePath());
		}
		return list;
	}
	
	
	private List<File> scan(File file , List<File> files) {
		if (file.isDirectory()) {
			for (File f : file.listFiles(fileExtensionFilter))
				scan(f, files);
		}
		else {
			files.add(file);
		}
		return files;
	}
}
