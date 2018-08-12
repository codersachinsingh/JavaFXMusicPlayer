package musicplayer.media;

import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class MediaFileExtensionFilter implements FileFilter {
	Set<String> extensions = new TreeSet<>();
	public MediaFileExtensionFilter(String[] list) {
		for (String s : list) {
			extensions.add(s);
		}
	}
	
	public MediaFileExtensionFilter() {
		
	}
	
	public void addExtension(String s) {
		extensions.add(s);
	}
	public void addExtension(String[] s) {
		for(String str : s)
		extensions.add(str);
	}
	public void remove(String s) {
		extensions.remove(s);
	}
	public void remove(String[] s) {
		for (String str : s)
		extensions.remove(str);
	}
	
	@Override
	public boolean accept(File file) {
		if (file.isDirectory())
			return true;
		Iterator<String> iterator = extensions.iterator();
		String filename = file.getName();
		int dotIndex = filename.lastIndexOf('.');
		if (dotIndex < 0)
			return false;
		else
			dotIndex++;
		String fileExtension = filename.substring(dotIndex, filename.length());
		while(iterator.hasNext()) {
			if (fileExtension.compareToIgnoreCase(iterator.next())==0)
				return true;
		}
		return false;
	}

}
