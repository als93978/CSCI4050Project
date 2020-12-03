package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.BookDA;
import models.Book;
import models.ErrorMessage;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookDA bookDA = new BookDA();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
			searchBooks(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage(), "search.jsp");
		}
	}

	private void searchBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String searchKeyword = request.getParameter("keyword");
		List<Book> keywordBooks = bookDA.getBooksByKeyword(searchKeyword);
		
		if(keywordBooks != null)
			request.setAttribute("keywordBooks", keywordBooks);
		
		redirectToPage(request, response, "search.jsp");
	}
	
//	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
//		Message message = new Message();
//		
//		message.setMessage(messageStr);
//		
//		request.setAttribute("message", message);
//		
//		redirectToPage(request, response, "ManageBooks");
//	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message, String redirectTo) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, redirectTo);
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
