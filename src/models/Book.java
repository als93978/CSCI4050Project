package models;

public class Book {
	private int bookID;
	private String title;
	private String author;
	private float sellingPrice;
	private String isbn;
	private String genre;
	private String description;
	private int publicationYear;
	private String imagePath;
	private float buyPrice;
	private int edition;
	private String publisher;
	private int quantity;
	private int minThreshold;
	private boolean archived;
	private BookMarketingAttribute marketingAttribute;
	
	public int getBookID() {
		return bookID;
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public float getSellingPrice() {
		return sellingPrice;
	}
	
	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPublicationYear() {
		return publicationYear;
	}
	
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public float getBuyPrice() {
		return buyPrice;
	}
	
	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
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
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getMinThreshold() {
		return minThreshold;
	}
	
	public void setMinThreshold(int minThreshold) {
		this.minThreshold = minThreshold;
	}
	
	public boolean getArchived() {
		return archived;
	}
	
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	
	public BookMarketingAttribute getMarketingAttribute() {
		return marketingAttribute;
	}
	
	public void setMarketingAttribute(BookMarketingAttribute marketingAttribute) {
		this.marketingAttribute = marketingAttribute;
	}
}
