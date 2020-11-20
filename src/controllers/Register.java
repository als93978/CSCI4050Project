package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
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
import models.Email;
import models.ErrorMessage;
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
    
	private UserDA userDA = new UserDA();
	private AddressDA addressDA = new AddressDA();
	private PaymentCardDA paymentCardDA = new PaymentCardDA();
	
	private User user = null;
	private Address address = null;
	private PaymentCard paymentCard = null;
	
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
		try {
			processUserInfo(request);
			
			sendConfirmationEmail(request);
			
			redirectToPage(request, response, "registrationConfirmation.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			interpretAndReturnException(request, response, e);
		}
	}
	
	private void processUserInfo(HttpServletRequest request) throws Exception {
		user = initUser(request);
		
		userDA.createUser(user);
		
		user = userDA.getLastUser(); // So we can get the userID, b/c we don't necessarily know it beforehand
		
		boolean paymentInfoEntered = paymentInfoWasEntered(request);
		boolean shippingInfoEntered = request.getParameter("shippingOption") != null;
		
		if(paymentInfoEntered) {
			paymentCard = initPaymentCard(request);
			
			paymentCardDA.createPaymentCard(paymentCard);
			
			// Set NumOfCards to 1
//			UserDAOld.editUserValue(newUser.getUserID(), "NumOfCards", 1);
			user.setNumOfCards(1);
			userDA.updateUser(user);
		}
		
		if(shippingInfoEntered) {
			address = initShippingInfo(request);
			
			addressDA.createAddress(address);
			
			// Update shippingInfo model to get AddressID
			address = addressDA.getLastAddress();
			
			// Now go back to newUser and enter the addressID
//			UserDAOld.editUserValue(newUser.getUserID(), "AddressID", shippingInfo.getAddressID());
			user.setAddressID(address.getAddressID());
			userDA.updateUser(user);
		}
	}
	
	private void sendConfirmationEmail(HttpServletRequest request) throws SQLException, MessagingException {
		user = userDA.getLastUser();
		
		String confirmationCode = ConfirmUser.generateConfirmationCode();
		
//		UserDAOld.editUserValue(newUser.getUserID(), "ConfirmationCode", confirmationCode);
		
		user.setConfirmationCode(confirmationCode);
		userDA.updateUser(user);
		
		String fromAddress = EmailHelper.fromAddress;
		String userEmail = user.getEmail();
		
		Email email = new Email();
		
		email.setFromAddress(fromAddress);
		email.setToAddress(userEmail);
		email.setBody(email.getConfirmationBody() + confirmationCode);
		
		EmailHelper emailHelper = new EmailHelper();
		emailHelper.sendConfirmationEmail(email);
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
	private PaymentCard initPaymentCard(HttpServletRequest request) {
		PaymentCard paymentCard = new PaymentCard();
		
		String cardNum = request.getParameter("cardNum");
		int cardNumInt = Integer.parseInt(request.getParameter("paymentMethod"));
		
		int cardTypeNum = Integer.parseInt(request.getParameter("paymentMethod"));
		// Using ints seems to be easiest way to input an enum from html
		CardType[] cardTypeValues = CardType.values();
		CardType cardType = cardTypeValues[cardTypeNum-1];
		
		String expDate = request.getParameter("expDate");
		
		// Need to read previously added user to get its userID
//		User newUser = UserDA.getLastUserFromDB();
//		int userID = newUser.getUserID();
		int userID = user.getUserID();
		
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
	
	private void interpretAndReturnException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + e.getMessage());
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "registration.jsp");
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
