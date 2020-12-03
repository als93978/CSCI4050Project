package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.BookDA;
import dataAccess.OrderDA;
import dataAccess.OrderItemDA;
import dataAccess.UserDA;
import models.Book;
import models.ErrorMessage;
import models.Order;
import models.OrderItem;
import models.User;
import models.UserType;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class LoadShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDA userDA = new UserDA();
	private OrderDA orderDA = new OrderDA();
	private OrderItemDA orderItemDA = new OrderItemDA();
	private BookDA bookDA = new BookDA();
	
	private User user = null;
	private Order order = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadShoppingCart() {
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
	
	private void checkUserLoggedIn(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Cookie[] cookies = request.getCookies();
		
		if(cookies.length > 1) {
			if(cookies[1].getName().equals("userID")) {
				user = userDA.getUserByID(Integer.parseInt(cookies[1].getValue()));
				
				processSessionCookie(request, response, cookies[1]);
			}
			
			else {
				String mustBeLoggedInMsg = "You must be logged in to access this page.";
				
				returnError(request, response, mustBeLoggedInMsg, "login.jsp");			
			}
		}
		
		else {
			String mustBeLoggedInMsg = "You must be logged in to access this page.";
			
			returnError(request, response, mustBeLoggedInMsg, "login.jsp");
		}
	}
	
	private void processSessionCookie(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) throws NumberFormatException, Exception {
		if(isUserAnAdmin()) {
			String isAdminMsg = "Admins do not have shopping carts. Please login as a customer.";
			
			returnError(request, response, isAdminMsg, "login.jsp");		
		}
		
		else {
			loadShoppingCartInformation(request, response);
		}
	}
	
	private boolean isUserAnAdmin() throws SQLException {
		UserType userType = user.getType();
		
		if(userType.equals(UserType.ADMIN))
			return true;
		
		return false;
	}
	
	private void loadShoppingCartInformation(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		order = orderDA.getNonSubmittedOrderByUserID(user.getUserID());

		if(order != null) { // user has a non-submitted order
			List<OrderItem> orderItems = orderItemDA.getOrderItemsByOrderID(order.getOrderID());
			
			List<Book> booksForOrderItems = null;
			if(orderItems != null)
				booksForOrderItems = bookDA.getBooksByOrderItems(orderItems);
			
			if(orderItems != null)
				request.setAttribute("orderItems", orderItems);
			
			if(booksForOrderItems != null)
				request.setAttribute("booksForOrderItems", booksForOrderItems);
		}
		
		redirectToPage(request, response, "shoppingCart.jsp");
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
