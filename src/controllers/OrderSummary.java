package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataAccess.OrderDA;
import models.Book;
import models.ErrorMessage;
import models.Order;
import models.OrderItem;

/**
 * Servlet implementation class OrderSummary
 */
@WebServlet("/OrderSummary")
public class OrderSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private OrderDA orderDA = new OrderDA();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSummary() {
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
			calculateOrderValues(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void calculateOrderValues(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession infoSession = request.getSession();
		
		List<OrderItem> orderItems = (List<OrderItem>) infoSession.getAttribute("orderItems");
		
		if(orderItems == null) {
			String emptyCartMsg = "Your cart is empty. Please add an item to your cart before attempting to continue.";
			
			returnError(request, response, emptyCartMsg);
		}
		
		List<Book> booksForOrderItems = (List<Book>) infoSession.getAttribute("booksForOrderItems");
		Order order = (Order) infoSession.getAttribute("order");
		
		float subtotal = 0.0f;
		float tax = 0.0f;
		float total = 0.0f;
		
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		
		// Calculate subtotal (all order items price * quantity)
		for(int i = 0; i < orderItems.size(); i++) {
			OrderItem currentOrderItem = orderItems.get(i);
			Book currentBook = booksForOrderItems.get(i);
			
			int quantity = currentOrderItem.getQuantity();
			float price = currentBook.getSellingPrice();
			
			subtotal += (quantity * price);
		}
		
		// Calculate tax (subtotal * taxRate)
		float taxRate = 0.07f;
		
		tax = subtotal * taxRate;
		
		// Calculate total ((subtotal + tax) - promoDiscount)
		int promoRawPercent = order.getPromotionCode();
		
		if(promoRawPercent != 0) {
			float promoPercent = promoRawPercent / 100.0f;
			float subtotalBeforeDiscount = subtotal + tax;
			float promoDiscount = subtotalBeforeDiscount * promoPercent;
			
			total = subtotalBeforeDiscount - promoDiscount;
			
			request.setAttribute("promoRawPercent", promoRawPercent);
			request.setAttribute("promoDiscount", promoDiscount);
		}
		
		else {
			total = subtotal + tax;
		}
		
		// Set TotalPrice in Order in db
		order.setTotalPrice(total);
		orderDA.updateOrder(order);
		
		request.setAttribute("subtotal", decimalFormat.format(subtotal));
		request.setAttribute("tax", decimalFormat.format(tax));
		request.setAttribute("total", decimalFormat.format(total));
		
		request.setAttribute("orderItems", orderItems);
		request.setAttribute("booksForOrderItems", booksForOrderItems);
		request.setAttribute("order", order);
		
		redirectToPage(request, response, "orderSummary.jsp");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "shoppingCart.jsp");
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
