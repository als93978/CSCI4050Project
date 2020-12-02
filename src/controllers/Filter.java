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
import models.SearchFilter;

/**
 * Servlet implementation class Filter
 */
@WebServlet("/Filter")
public class Filter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDA bookDA = new BookDA();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter() {
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
			filterAndRedirect(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage(), "search.jsp");
		}
	}
	
	private void filterAndRedirect(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String genre = request.getParameter("genre");
		String isbn = request.getParameter("isbn");
		String author = request.getParameter("author");
		
		float fromPrice = -1;
		String fromPriceInput = request.getParameter("fromPrice");
		if(fromPriceInput != null && fromPriceInput != "")
			fromPrice = Float.parseFloat(fromPriceInput);
		
		float toPrice = -1;
		String toPriceInput = request.getParameter("toPrice");
		if(toPriceInput != null && toPriceInput != "")
			toPrice = Float.parseFloat(toPriceInput);
		
		int edition = -1;
		String editionInput = request.getParameter("edition");
		if(editionInput != null && editionInput != "")
			edition = Integer.parseInt(editionInput);
		
		String publisher = request.getParameter("publisher");
		
		int publicationYear = -1;
		String publicationYearInput = request.getParameter("publicationYear");
		if(publicationYearInput != null && publicationYearInput != "")
			publicationYear = Integer.parseInt(publicationYearInput);
		
		SearchFilter filter = new SearchFilter();
		filter.setGenre(genre);
		filter.setIsbn(isbn);
		filter.setAuthor(author);
		filter.setFromPrice(fromPrice);
		filter.setToPrice(toPrice);
		filter.setEdition(edition);
		filter.setPublisher(publisher);
		filter.setPublicationYear(publicationYear);
		
		List<Book> filteredBooks = bookDA.getBooksByFilter(filter);
		
		if(filteredBooks != null) {
			request.setAttribute("keywordBooks", filteredBooks);
		}
		
		redirectToPage(request, response, "search.jsp");
	}
	
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
