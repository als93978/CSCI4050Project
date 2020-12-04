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
import javax.servlet.http.HttpSession;

import dataAccess.OrderDA;
import dataAccess.PromotionDA;
import models.Book;
import models.ErrorMessage;
import models.Message;
import models.Order;
import models.OrderItem;
import models.Promotion;

/**
 * Servlet implementation class ApplyPromo
 */
@WebServlet("/ApplyPromo")
public class ApplyPromo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PromotionDA promotionDA = new PromotionDA();
	private OrderDA orderDA = new OrderDA();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyPromo() {
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
			findAndApplyPromo(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage());
		}
	}
	
	private void findAndApplyPromo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int promoCode = Integer.parseInt(request.getParameter("promoCode"));
		
		Promotion promotion = promotionDA.getPromotionByCode(promoCode);
		
		HttpSession infoSession = request.getSession();
		
		List<OrderItem> orderItems = (List<OrderItem>) infoSession.getAttribute("orderItems");
		List<Book> booksForOrderItems = (List<Book>) infoSession.getAttribute("booksForOrderItems");
		Order order = (Order) infoSession.getAttribute("order");
		
		String subtotal = (String) infoSession.getAttribute("subtotal");										
		String tax = (String) infoSession.getAttribute("tax");
		String total = (String) infoSession.getAttribute("total");
		
		if(promotion == null) {
			request.setAttribute("orderItems", orderItems);
			request.setAttribute("booksForOrderItems", booksForOrderItems);
			request.setAttribute("order", order);
			
			request.setAttribute("subtotal", subtotal);
			request.setAttribute("tax", tax);
			request.setAttribute("total", total);
			
			String promoNotFoundMsg = "Promotion code entered was not found.";
			returnError(request, response, promoNotFoundMsg);
		}
		
		else {
			order.setPromotionCode(promoCode);
			orderDA.updateOrder(order);
			
			request.setAttribute("orderItems", orderItems);
			request.setAttribute("booksForOrderItems", booksForOrderItems);
			request.setAttribute("order", order);
			
			request.setAttribute("subtotal", subtotal);
			request.setAttribute("tax", tax);
			request.setAttribute("total", total);
			
			redirectToPage(request, response, "OrderSummary");
		}
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
