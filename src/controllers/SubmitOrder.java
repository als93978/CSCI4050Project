package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataAccess.OrderDA;
import models.Address;
import models.Book;
import models.ErrorMessage;
import models.Order;
import models.OrderItem;
import models.OrderStatus;
import models.PaymentCard;

/**
 * Servlet implementation class SubmitOrder
 */
@WebServlet("/SubmitOrder")
public class SubmitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private OrderDA orderDA = new OrderDA();
	
	private Order order = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrder() {
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
			submitOrder(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void submitOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession infoSession = request.getSession();
		
		order = (Order) infoSession.getAttribute("order");
		PaymentCard paymentCard = (PaymentCard) infoSession.getAttribute("paymentCard");
		Address address = (Address) infoSession.getAttribute("address");
		List<OrderItem> orderItems = (List<OrderItem>) infoSession.getAttribute("orderItems");
		List<Book> booksForOrderItems = (List<Book>) infoSession.getAttribute("booksForOrderItems");
		
		Date dateTime = new Date();
		order.setOrderDateTime(dateTime.toString());
		
		String paymentMethod = paymentCard.getCardType().name();
		order.setPaymentMethod(paymentMethod);
		
		String cardNum = paymentCard.getCardNum();
		order.setCardNum(cardNum);
		
		int addressID = address.getAddressID();
		order.setAddressID(addressID);
		
		order.setOrderStatus(OrderStatus.SUBMITTED);
		
		orderDA.updateOrder(order);
		
		request.setAttribute("order", order);
		request.setAttribute("orderItems", orderItems);
		request.setAttribute("booksForOrderItems", booksForOrderItems);
		
		redirectToPage(request, response, "OrderConfirmation");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "orderSummary.jsp");
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
