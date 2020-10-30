package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.AddressDA;
import dataAccess.PaymentCardDA;
import dataAccess.UserDA;
import models.Address;
import models.CardType;
import models.PaymentCard;
import models.UserStatus;
import models.UserType;
import models.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		User newUser = initUser(request);
		
		//UserDA userDA = new UserDA();
		UserDA.addUserToDB(newUser);
		
		// Update newUser model so we don't have to read from DB for paymentInfo and shippingInfo below
		newUser = UserDA.getLastUserFromDB();
		
		boolean paymentInfoEntered = paymentInfoWasEntered(request);
		boolean shippingInfoEntered = request.getParameter("shippingOption") != null;
		
		if(paymentInfoEntered) {
			PaymentCard paymentCard = initPaymentCard(request, newUser);
			
			PaymentCardDA.addPaymentCardToDB(paymentCard);
			
			// Increment NumOfCards
			UserDA.editUserValue(newUser.getUserID(), "NumOfCards", 1);
			
			newUser = UserDA.getLastUserFromDB();
		}
		
		if(shippingInfoEntered) {
			Address shippingInfo = initShippingInfo(request);
			
			AddressDA.addAddressToDB(shippingInfo);
			
			// Update shippingInfo model to get AddressID
			shippingInfo = AddressDA.getLastAddressFromDB();
			
			// Now go back to newUser and enter the addressID
			UserDA.editUserValue(newUser.getUserID(), "AddressID", shippingInfo.getAddressID());
		}
	}
	
	// Set user info that was entered on registration page and return the new User
	private User initUser(HttpServletRequest request) {
		User user = new User();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserStatus status = UserStatus.INACTIVE;
		boolean enrollmentForPromotions = request.getParameter("enrollmentForPromotions") != null;
		int numOfCards = 0;
		UserType type = UserType.CUSTOMER;
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setStatusID(status);
		user.setEnrollmentForPromotions(enrollmentForPromotions);
		user.setNumOfCards(numOfCards);
		user.setType(type);
		
		return user;
	}
	
	// Set payment info user optionally entered on registration page and return the PaymentCard
	private PaymentCard initPaymentCard(HttpServletRequest request, User newUser) {
		PaymentCard paymentCard = new PaymentCard();
		
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		int cardNumInt = Integer.parseInt(request.getParameter("paymentMethod"));
		
		// Using ints seems to be easiest way to input an enum from html
		CardType[] cardTypeValues = CardType.values();
		CardType cardType = cardTypeValues[cardNumInt-1];
		
		String expDate = request.getParameter("expDate");
		
		// Need to read previously added user to get its userID
//		User newUser = UserDA.getLastUserFromDB();
//		int userID = newUser.getUserID();
		int userID = newUser.getUserID();
		
		paymentCard.setCardNum(cardNum);
		paymentCard.setCardType(cardType);
		paymentCard.setExpDate(expDate);
		paymentCard.setUserID(userID);
		
		return paymentCard;
	}
	
	private Address initShippingInfo(HttpServletRequest request) {
		Address shippingInfo = new Address();
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zipCode = Integer.parseInt(request.getParameter("zipCode"));
		
		shippingInfo.setStreet(street);
		shippingInfo.setCity(city);
		shippingInfo.setState(state);
		shippingInfo.setZipCode(zipCode);
		
		return shippingInfo;
	}
	
	private boolean paymentInfoWasEntered(HttpServletRequest request) {
		String paymentInfoQuestionValue = request.getParameter("inlineRadioOptions");
		
		if(paymentInfoQuestionValue.equals("option1")) // Yes was chosen
			return true;
		
		return false; // Otherwise, No was chosen
	}

}