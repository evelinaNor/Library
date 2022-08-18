package lt.codeacademy.learn.library.entities;

import javax.persistence.*;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String title;
	private String description;
	private String category;
	private String author;
	
	public Book() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", description=" + description + ", category=" + category
				+ ", author=" + author + "]";
	}
	public Book(Integer id, String title, String description, String category, String author) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.author = author;
	}

	
	
}
