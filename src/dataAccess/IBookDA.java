package dataAccess;

import java.sql.SQLException;
import java.util.List;

import models.Book;

public interface IBookDA {
	public void createBook(Book book) throws SQLException;
	
	public List<Book> getAllBooks() throws SQLException;
	public List<Book> getAllFeaturedBooks() throws SQLException;
	public List<Book> getAllTopSellingBooks() throws SQLException;
	
	public Book getBookByID(int bookID) throws SQLException;
	public Book getBookByISBN(String isbn) throws SQLException;
	
	public Book getLastBook() throws SQLException;
	
	public void updateBook(Book book) throws SQLException;
	
	public void deleteBook(Book book) throws SQLException;
}
