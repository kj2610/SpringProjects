package com.infy.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
	@Id
	private Integer bookId;
	@Column(name = "book_name")
	private String title;
	private String authorName;

	@Column(name = "year")
	private LocalDate publishedYear;
	private String publisher;
	private Long isbn;
	private Integer price;
	
	public Book() {
		
	}
	
	public Book(Integer bookId, String title, String authorName, LocalDate publishedYear, String publisher, Long isbn,
			Integer price) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.authorName = authorName;
		this.publishedYear = publishedYear;
		this.publisher = publisher;
		this.isbn = isbn;
		this.price = price;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public LocalDate getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(LocalDate publishedYear) {
		this.publishedYear = publishedYear;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		return Objects.hash(authorName, bookId, isbn, price, publishedYear, publisher, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authorName, other.authorName) && Objects.equals(bookId, other.bookId)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(price, other.price)
				&& Objects.equals(publishedYear, other.publishedYear) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", authorName=" + authorName + ", publishedYear="
				+ publishedYear + ", publisher=" + publisher + ", isbn=" + isbn + ", price=" + price + "]";
	}
	
	
	

}
