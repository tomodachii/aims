package media;

public class Disc extends Media {
	protected int length;
	protected String director;
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Disc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Disc(String title, String category, float cost) {
		super(title, category, cost);
		// TODO Auto-generated constructor stub
	}

	public Disc(String title, String category) {
		super(title, category);
		// TODO Auto-generated constructor stub
	}

	public Disc(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public Disc(String title, String category, float cost, int length) {
		super(title, category, cost);
		this.length = length;
	}
	
	public Disc(String title, String category, float cost, int length, String director) {
		super(title, category, cost);
		this.length = length;
		this.director = director;
	}
	
}
