package demo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Class {
	private List<Student> students;
	private Map<String, Book> favoriteBooks;

	public Class() {}
	public Class(List<Student> students) {
		super();
		this.students = students;
	}
	public Class(List<Student> students, Map<String, Book> favoriteBooks) {
		this.students = students;
		this.favoriteBooks = favoriteBooks;
	}
	
	public void initialize() {
        System.out.println("Class initialized");
    }
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Map<String, Book> getFavoriteBooks() {
		return favoriteBooks;
	}
	public void setFavoriteBooks(Map<String, Book> favoriteBooks) {
		this.favoriteBooks = favoriteBooks;
	}
	
	@Override
	public String toString() {
		return "Class [students=" + students + ", favoriteBooks=" + favoriteBooks + "]";
	}
}
