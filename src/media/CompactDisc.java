package media;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable{
	private String artist;
	private List<Track> tracks = new ArrayList<Track>();

	public CompactDisc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Track searchTrack(String title) {
		for (Track item : tracks) {
			if (item.getTitle() == title) {
				return item;
			}
		}
		return null;
	}

	public boolean addTrack(Track item) {
		if (searchTrack(item.getTitle()) != null) {
			System.out.println("(!) Item already exist!");
			return false;
		}
		tracks.add(item);
		return true;
	}

	public boolean addTrack(Track... trackList) {
		for (int i = 0; i < trackList.length; i++) {
			if (!addTrack(trackList[i])) {
				return false;
			}
		}
		return true;
	}

	public void removeTrack(Track item) {
		if (searchTrack(item.getTitle()) != null) {
			tracks.remove(item);
		}
	}

	public int getLength() {
		int sum = 0;
		for (Track item : tracks) {
			sum += item.getLength();
		}
		return sum;
	}
	
	public int getCdSize() {
		return tracks.size();
	}
	
	public void play() {
		System.out.println("CD title: " + this.title);
		System.out.println("Artist: " + this.artist);
		for (Track item: tracks) {
			item.play();
		}
	}

	public CompactDisc(String title, String category, float cost, int length, String director) {
		super(title, category, cost, length, director);
		// TODO Auto-generated constructor stub
	}

	public CompactDisc(String title, String category, float cost, int length) {
		super(title, category, cost, length);
		// TODO Auto-generated constructor stub
	}

	public CompactDisc(String title, String category, float cost) {
		super(title, category, cost);
		// TODO Auto-generated constructor stub
	}

	public CompactDisc(String title, String category) {
		super(title, category);
		// TODO Auto-generated constructor stub
	}

	public CompactDisc(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public CompactDisc(String title, String category, float cost, String artist, ArrayList<Track> tracks) {
		super(title, category, cost);
		this.artist = artist;
		this.tracks = tracks;
	}
	public CompactDisc(String title, String category, float cost, String artist,Track ...tracks) {
		super(title, category, cost);
		this.artist = artist;
		for (Track t: tracks) {
			this.tracks.add(t);
		}
	}
	
	public int compareTo(Object o) {
		if (o instanceof CompactDisc) {
			CompactDisc cd = (CompactDisc) o;
			if (Integer.compare(this.getCdSize(), cd.getCdSize()) == 0) {
				return Integer.compare(this.getLength(), cd.getLength());
			} else {
				return Integer.compare(this.getCdSize(), cd.getCdSize());
			}
		}
		return -99999;
	}
}
