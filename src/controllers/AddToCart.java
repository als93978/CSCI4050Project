package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.OrderDA;
import dataAccess.OrderItemDA;
import dataAccess.ShoppingCartDA;
import dataAccess.UserDA;
import models.ErrorMessage;
import models.Order;
import models.OrderItem;
import models.ShoppingCart;
import models.User;
import models.UserType;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDA userDA = new UserDA();
	private OrderDA orderDA = new OrderDA();
	private OrderItemDA orderItemDA = new OrderItemDA();
	private ShoppingCartDA shoppingCartDA = new ShoppingCartDA();
	
	private User user = null;
	private Order order = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
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
				String mustBeLoggedInMsg = "You must be logged in to add an item to your cart.";
				
				returnError(request, response, mustBeLoggedInMsg, "login.jsp");			
			}
		}
		
		else {
			String mustBeLoggedInMsg = "You must be logged in to add an item to your cart.";
			
			returnError(request, response, mustBeLoggedInMsg, "login.jsp");
		}
	}
	
	private void processSessionCookie(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) throws NumberFormatException, Exception {
		if(isUserAnAdmin()) {
			String isAdminMsg = "Admins cannot add items to carts. Please login as a customer.";
			
			returnError(request, response, isAdminMsg, "login.jsp");		
		}
		
		else {
			createOrderIfNotExists();
			addBookToCart(request, response);
		}
	}
	
	private boolean isUserAnAdmin() throws SQLException {
		UserType userType = user.getType();
		
		if(userType.equals(UserType.ADMIN))
			return true;
		
		return false;
	}
	
	private void createOrderIfNotExists() throws SQLException {
		order = orderDA.getOrderByUserID(user.getUserID());
		
		if(order == null) {
			order = new Order();
			order.setUserID(user.getUserID());
			
			orderDA.createOrder(order);
			
			order = orderDA.getOrderByUserID(user.getUserID());
			
			ShoppingCart cart = shoppingCartDA.getShoppingCartByUserID(user.getUserID());
			cart.setOrderID(order.getOrderID());
			
			shoppingCartDA.updateShoppingCart(cart);
			
			// Shopping cart OrderID not updating
		}
	}
	
	private void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int bookID = Integer.parseInt(request.getParameter("bookID"));
		
		OrderItem orderItem = orderItemDA.getOrderItem(bookID, order.getOrderID());
		
		if(orderItem == null) {
			orderItem = new OrderItem();
			orderItem.setBookID(bookID);
			
			System.out.println("orderID in addBookToCart(): " + order.getOrderID());
			
			orderItem.setOrderID(order.getOrderID());
			orderItem.setQuantity(1);
			
			orderItemDA.createOrderItem(orderItem);
		}
		
		else {
			System.out.println("orderItem.getQuantity: " + orderItem.getQuantity());
			
			orderItem.setQuantity(orderItem.getQuantity() + 1);
			
			orderItemDA.updateOrderItem(orderItem);
		}
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
