package media;

public class DigitalVideoDisc extends Disc implements Playable{

	public boolean search(String title) {
		String[] partsList1 = title.toLowerCase().split(" ");
		String[] partsList2 = this.title.toLowerCase().split(" ");
		int i = 0;
		for (String item1 : partsList1) {
			for (String item2 : partsList2) {
				if (item1.equals(item2)) {
					i++;
					break;
				}
			}
		}
		if (i == partsList2.length) {
			return true;
		}
		return false;
	}
	
	public void play() {
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}

	// Constructor
	public DigitalVideoDisc() {
		this.title = "noname";
		this.category = "unknown";
		this.director = "unknown";
		this.length = 0;
		this.cost = 0.0f;
	}

	public DigitalVideoDisc(String title, String category, float cost, int length, String director) {
		super(title, category, cost, length, director);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title, String category, float cost, int length) {
		super(title, category, cost, length);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title, String category, float cost) {
		super(title, category, cost);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title, String category) {
		super(title, category);
		// TODO Auto-generated constructor stub
	}

	public DigitalVideoDisc(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public int compareTo(Object o) {
		if (o instanceof DigitalVideoDisc) {
			DigitalVideoDisc dvd = (DigitalVideoDisc) o;
			return Float.compare(this.getCost(), dvd.getCost());
		}
		return -99999;
	}
}
