package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import dataAccess.PaymentCardDA;
import dataAccess.UserDA;
import models.CardType;
import models.Email;
import models.ErrorMessage;
import models.Message;
import models.PaymentCard;
import models.Promotion;
import models.User;

/**
 * Servlet implementation class SendPayment
 */
@WebServlet("/SendPayment")
public class SendPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentCardDA paymentCardDA = new PaymentCardDA();
	private User user = new User();
	
	private PaymentCard paymentCard = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendPayment() {
        super();
        // TODO Auto-generated constructor stub
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
			processPaymentCard(request);
			
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage());
		}
	}
	private void processPaymentCard(HttpServletRequest request) throws Exception {
		paymentCard = initPaymentCard(request);
		paymentCardDA.createPaymentCard(paymentCard);
	}
	
	private PaymentCard initPaymentCard(HttpServletRequest request) {
		PaymentCard paymentCard = new PaymentCard();
		
		String cardNum = request.getParameter("cardNum");
		String cardType = request.getParameter("cardType");
		String expDate = request.getParameter("expDate");
		
		CardType type = CardType.DISCOVER;
		if (cardType == "VISA") {
			type = CardType.VISA;
		}
		if (cardType == "MASTERCARD") {
			type = CardType.MASTERCARD;
		}
		if (cardType == "AMERICANEXPRESS") {
			type = CardType.AMERICANEXPRESS;
		}
		
		paymentCard.setCardNum(cardNum);
		paymentCard.setCardType(type);
		paymentCard.setExpDate(expDate);
		paymentCard.setUserID(1);
		
		return paymentCard;
	}
	
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "shoppingCart.jsp");
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
