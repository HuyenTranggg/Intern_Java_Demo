package demo;

import java.util.Set;

public class Book {
	private String name;
	private double price;
	private Author author;
	private Set<Author> coAuthors;
	
	public Book() {
		System.out.println("Book constructor called");
	}
	public Book(String name, double price) {
		this.name = name;
		this.price = price;
	}
	public Book(String name, double price, Author author) {
		this.name = name;
		this.price = price;
		this.author = author;
		System.out.println("Book constructor has argument called");
	}
	public Book(String name, double price, Author author, Set<Author> coAuthors) {
		this.name = name;
		this.price = price;
		this.author = author;
		this.coAuthors = coAuthors;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Set<Author> getCoAuthors() {
		return coAuthors;
	}
	public void setCoAuthors(Set<Author> coAuthors) {
		this.coAuthors = coAuthors;
	}
	
	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + ", author=" + author + ", coAuthors=" + coAuthors + "]";
	}
}
