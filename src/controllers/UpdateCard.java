package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.AddressDA;
import dataAccess.PaymentCardDA;
import models.Address;
import models.CardType;
import models.ErrorMessage;
import models.Message;
import models.PaymentCard;
import models.UserStatus;
import models.UserType;
import models.User;

/**
 * Servlet implementation class UpdateCard
 */
@WebServlet("/UpdateCard")
public class UpdateCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PaymentCardDA paymentCardDA = new PaymentCardDA();
	
	private PaymentCard paymentCard = null;
	
    /**
     * Default constructor. 
     */
    public UpdateCard() {
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
			Cookie[] cookies = request.getCookies();
	        int userID = Integer.parseInt(cookies[1].getValue());
	        
	        String cardNum = request.getParameter("cardNum");
	        
	        int cardTypeNum = Integer.parseInt(request.getParameter("paymentMethod"));
	        CardType[] cardTypeValues = CardType.values();
	        CardType cardType = cardTypeValues[cardTypeNum-1];
	        
	        String expDate = request.getParameter("expDate");
	
	        paymentCard = paymentCardDA.getPaymentCardByUserID(userID);
	        
	        if(paymentCard == null) {
	        	paymentCard = new PaymentCard();
	        	paymentCard.setCardNum(cardNum);
	        	paymentCard.setCardType(cardType);
	        	paymentCard.setExpDate(expDate);
	        	paymentCard.setUserID(userID);
	        	
	        	paymentCardDA.createPaymentCard(paymentCard);
	        }
	        
	        else {
	        	paymentCard.setCardNum(cardNum);
	        	paymentCard.setCardType(cardType);
	        	paymentCard.setExpDate(expDate);
	        	paymentCard.setUserID(userID);
	        	
	        	paymentCardDA.updateCardNum(paymentCard);
	        }
	        	
	        String message = "Payment Card changes saved.";
	        returnMessage(request, response, message);
		} catch(Exception e) {
			e.printStackTrace();
			interpretAndReturnException(request, response, e);
		}
	}
	
	private void interpretAndReturnException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		returnError(request, response, e.getMessage());
	}
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "EditProfile");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "EditProfile");
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
