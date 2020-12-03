package controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.BookDA;
import dataAccess.UserDA;
import models.Book;
import models.User;
import models.UserType;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDA userDA = new UserDA();
	private BookDA bookDA = new BookDA();
	
	private User user = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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
			checkUserLoggedIn(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Cookie checkUserLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			if(cookies.length > 1) {
				if(cookies[1].getName().equals("userID")) {
					processSessionCookie(request, response, cookies[1]);
				}
			}
		}

		// If the above fails
		loadHomepageBooks(request, response, "index.jsp");
		
		return null;
	}
	
	private void processSessionCookie(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) throws SQLException, IOException {
		int userID = Integer.parseInt(sessionCookie.getValue());
		
		user = userDA.getUserByID(userID);
		
		boolean isUserAdmin = checkUserIsAdmin();
		
		if(isUserAdmin) {
			response.sendRedirect(request.getContextPath() + "/ManageBooks");
		}
		
		else {
			loadHomepageBooks(request, response, "homepageWithUserIcon.html");
		}
	}
	
	private void loadHomepageBooks(HttpServletRequest request, HttpServletResponse response, String redirectPage) throws SQLException {
		List<Book> featuredBooks = bookDA.getAllFeaturedBooks();
		List<Book> topSellingBooks = bookDA.getAllTopSellingBooks();
		
		if(featuredBooks != null)
			request.setAttribute("featuredBooks", featuredBooks);
		
		if(topSellingBooks != null)
			request.setAttribute("topSellingBooks", topSellingBooks);
		
		redirectToPage(request, response, redirectPage);
	}
	
	private boolean checkUserIsAdmin() throws SQLException {
		UserType userType = user.getType();
		
		if(userType.equals(UserType.ADMIN))
			return true;
		
		return false;
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
