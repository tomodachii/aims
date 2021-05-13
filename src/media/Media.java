package media;

public abstract class Media<T> implements Comparable<T> {
	protected String title;
	protected String category;
	protected Float cost;
	protected Integer id;
	protected String type = getType();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Media() {
	}
	
	public Media(String title) {
		this.title = title;
	}
	
	public Media(String title, String category) {
		this(title);
		this.category = category;
	}
	
	public Media(String title, String category, float cost) {
		this(title, category);
		this.cost = cost;
	}
	
//	public boolean equals(Object o) {
//		if (o instanceof Media) {
//			Media m = (Media) o;
//			return (this.getId().equals(m.getId()));
//		}
//		return false;
//	}
	
	public int compareTo(T o) {
		if (o instanceof Media) {
			Media m = (Media) o;
			return this.getTitle().compareTo(m.getTitle());
		}
		return -99999;
	}
	
	public String getType() {
		if (this instanceof Book) {
			return "Book";
		} else if (this instanceof DigitalVideoDisc) {
			return "DVD";
		} else if (this instanceof CompactDisc){
			return "CD";
		} else {
			return null;
		}
	}
	
}
