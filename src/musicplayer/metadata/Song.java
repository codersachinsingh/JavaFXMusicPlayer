package musicplayer.metadata;

public class Song implements MusicFile {
	
	private String title;
	private String bitRate;
	private String sampleRate;
	private String fileLocation;
	private double duration;
	private MusicAlbum album;
	private MusicArtist artist;
	private MusicGenre genre;
	private boolean favorite;
	
	public Song(String title, String bitRate, String sampleRate, String fileLocation, double duration) {
		super();
		this.title = title;
		this.bitRate = bitRate;
		this.sampleRate = sampleRate;
		this.fileLocation = fileLocation;
		this.duration = duration;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getBitrate() {
		return bitRate;
	}

	@Override
	public String getSampleRate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFileLocation() {
		return fileLocation;
	}

	@Override
	public MusicAlbum getAlbum() {
		return album;
	}

	@Override
	public void setAlbum(MusicAlbum musicAlbum) {
		this.album = musicAlbum;
	}

	@Override
	public MusicArtist getArtist() {
		return artist;
	}

	@Override
	public void setArtist(MusicArtist artist) {
		this.artist = artist;
	}

	@Override
	public MusicGenre getGenre() {
		return this.genre;
	}

	@Override
	public void setGenre(MusicGenre genre) {
		this.genre = genre;
	}

	@Override
	public double getDuration() {
		return duration;
	}

	@Override
	public boolean isFavorite() {
		return favorite;
	}

	@Override
	public void setFavorit(boolean f) {
		this.favorite = f;
	}

	@Override
	public boolean equals(Object obj) {
		if (this==obj)
			return true;
		if (obj instanceof Song) {
			if (this.album.getAlbumTitle().equals(((Song) obj).album.getAlbumTitle()))
					return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String string  = "Title : " + title + "\n" + 
				"Album : " + album + "\n" + 
				"Artist : " + artist + "\n" + 
				"Genre : " + genre + "\n" + 
				"Bitrate : " + bitRate + "\n" + 
				"Sample Rate : " + sampleRate + "\n" + 
				"Duration : " + duration + "\n";
		return string;
	}
	
	
}