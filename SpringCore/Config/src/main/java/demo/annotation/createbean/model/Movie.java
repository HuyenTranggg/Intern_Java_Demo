package demo.annotation.createbean.model;

import org.springframework.stereotype.Component;

@Component
public class Movie {
    private String title;

    public Movie() {
    	System.out.println("Creating a bean with @Component annotation");
    }
    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
    public String toString() {
        return "Movie{" +
               "title='" + title + '\'' +
               '}';
    }
}
