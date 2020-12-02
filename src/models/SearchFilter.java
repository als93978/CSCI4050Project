package models;

public class SearchFilter {
	private String genre;
	private String isbn;
	private String author;
	private float fromPrice;
	private float toPrice;
	private int edition;
	private String publisher;
	private int publicationYear;
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public float getFromPrice() {
		return fromPrice;
	}
	
	public void setFromPrice(float fromPrice) {
		this.fromPrice = fromPrice;
	}
	
	public float getToPrice() {
		return toPrice;
	}
	
	public void setToPrice(float toPrice) {
		this.toPrice = toPrice;
	}
	
	public int getEdition() {
		return edition;
	}
	
	public void setEdition(int edition) {
		this.edition = edition;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getPublicationYear() {
		return publicationYear;
	}
	
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
}
