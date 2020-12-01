package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.BookDA;
import models.Book;
import models.ErrorMessage;
import models.Message;

/**
 * Servlet implementation class EditBook
 */
@WebServlet("/EditBook")
public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookDA bookDA = new BookDA();
	
	private Book book = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBook() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			editBook(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage());
		}
	}
	
	private void editBook(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int bookID = Integer.parseInt(request.getParameter("bookID"));

		book = bookDA.getBookByID(bookID);
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));
		String genre = request.getParameter("genre");
		String description = request.getParameter("description");

		if(!title.equals("") && title != null)
			book.setTitle(title);
		
		if(!author.equals("") && author != null)
			book.setAuthor(author);
		
		if(price != 0.0f)
			book.setSellingPrice(price);
		
		if(!genre.equals("") && genre != null)
			book.setGenre(genre);
		
		if(!description.equals("") && description != null)
			book.setDescription(description);

		bookDA.updateBook(book);
		
		String bookUpdatedMsg = "Book successfully updated. (BookID: " + bookID + ")";
		returnMessage(request, response, bookUpdatedMsg);
	}
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "ManageBooks");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "ManageBooks");
	}
	
	private void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + page);
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
