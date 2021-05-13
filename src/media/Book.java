package media;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Book extends Media {
	private List<String> authors = new ArrayList<String>();
	private String content;
	private List<String> contentTokens = new ArrayList<String>();
	private Map<String, Integer> wordFrequency = new HashMap<>();

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        processContent();
    }

	public void addAuthors(String authorsName) {
		if (!(authors.contains(authorsName))) {
			authors.add(authorsName);
		}
	}

	public void removeAuthors(String authorsName) {
		if (authors.contains(authorsName)) {
			authors.remove(authorsName);
		}
	}

	public Book(String title) {
		super(title);
	}

	public Book(String title, String category) {
		super(title, category);
	}

	public Book(String title, String category, float cost, List<String> authors) {
		super(title, category, cost);
		this.authors = authors;
	}

	public Book(String title, String category, float cost, String... authors) {
		super(title, category, cost);
		for (String a : authors) {
			this.authors.add(a);
		}
	}

	public void processContent() {
		String regex = "[!._,'@? ]";
		StringTokenizer str = new StringTokenizer(this.content, regex);
		while (str.hasMoreTokens()) {
			contentTokens.add(str.nextToken());
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
