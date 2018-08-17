package musicplayer.media;

import java.io.File;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import musicplayer.util.AppUtils;
import musicplayer.util.DataCollectionSerializer;

public class MediaFileScanner implements MediaScanner {
	private ObservableSet<String> allowedExtensions;
	private ObservableMap<String, List<String>> mediaFiles = FXCollections.observableHashMap();
	private MediaFileExtensionFilter fileExtensionFilter;
	private MediaLocator mediaLocator;
	private DataCollectionSerializer<Set<String>> extensionsSetSerializer;
	public MediaFileScanner(MediaLocator locator) {
		this.mediaLocator = locator;
		extensionsSetSerializer = new DataCollectionSerializer<>(AppUtils.ALLOWED_EXTENSIONS_FILE);
		Set<String> hibernatedSet = extensionsSetSerializer.retrieveState();
		if (hibernatedSet != null)
			allowedExtensions = FXCollections.observableSet(hibernatedSet);
		else
			allowedExtensions = FXCollections.observableSet();
		String[] strings = new String[allowedExtensions.size()];
		fileExtensionFilter = new MediaFileExtensionFilter(allowedExtensions.toArray(strings));
		refresh();
		mediaLocator.getMediaScanLocations().addListener(this::MediaLocationsChangeHandler);
		allowedExtensions.addListener(this::ExtensionsSetChangeLitsener);
	}
	@Override
	public ObservableMap<String, List<String>> getMediaFiles() {
		return mediaFiles;
	}
	@Override
	public List<String> getAllMediaFiles() {
		List<String> list = new ArrayList<>();
		for (String s : mediaFiles.keySet()) {
			list.addAll(mediaFiles.get(s));
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
	public void removeAllowedExtention(String extension) {
		allowedExtensions.remove(extension);
	}

	@Override
	public Iterator<String> getAllowedExtensions() {
		return allowedExtensions.iterator();
	}

	@Override
	public MediaLocator getMediaLocator() {
		return mediaLocator;
	}
	public ObservableSet<String> getMediaScanLocations() {return mediaLocator.getMediaScanLocations();}

	private void refresh() {
		for (String key : mediaLocator.getMediaScanLocations()) {
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

		Iterator<String> iterator = allowedExtensions.iterator();
		Set<String> set = new TreeSet<>();
		while (iterator.hasNext()) {
			set.add(iterator.next());
		}
		extensionsSetSerializer.saveState(set);
		refresh();
	}
	
	private List<String> scanDirectoryForMedia(String file){
		List<String> list = new ArrayList<>();
		File f = new File(file);
		List<File> files = new ArrayList<>();
		scan(f, files);
		for (File o : files) {
			list.add(o.getAbsolutePath());
		}
		return list;
	}

	private List<File> scan(File file , List<File> files) {
		if (file.isDirectory()) {
			for (File f : Objects.requireNonNull(file.listFiles(fileExtensionFilter)))
				scan(f, files);
		}
		else {
			files.add(file);
		}
		return files;
	}
}
