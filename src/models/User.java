package models;

import javax.servlet.http.HttpServletRequest;

public class User {
	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Status status;
	private boolean enrollmentForPromotions;
	private int numOfCards;
	private int typeID;
	private int addressID;
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatusID(Status status) {
		this.status = status;
	}
	
	public boolean isEnrollmentForPromotions() {
		return enrollmentForPromotions;
	}
	
	public void setEnrollmentForPromotions(boolean enrollmentForPromotions) {
		this.enrollmentForPromotions = enrollmentForPromotions;
	}
	
	public int getNumOfCards() {
		return numOfCards;
	}
	
	public void setNumOfCards(int numOfCards) {
		this.numOfCards = numOfCards;
	}
	
	public int getTypeID() {
		return typeID;
	}
	
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public int getAddressID() {
		return addressID;
	}
	
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	
	// Set user info that was entered on registration page
	public void initUser(HttpServletRequest request) {
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		email = request.getParameter("email");
		password = request.getParameter("password");
		status = Status.inactive;
		enrollmentForPromotions = request.getParameter("enrollmentForPromotions") != null;
		numOfCards = 0;
		
		boolean paymentInfoEntered = paymentInfoWasEntered(request);
		boolean shippingInfoEntered = request.getParameter("shippingOption") != null;
		
		if(paymentInfoEntered) {
			initPaymentCard(request);
		}
		
		if(shippingInfoEntered) {
			
		}
	}
	
	public PaymentCard initPaymentCard(HttpServletRequest request) {
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		
		int cardNumInt = Integer.parseInt(request.getParameter("paymentMethod"));
		CardType[] cardTypeValues = CardType.values();
		CardType cardType = cardTypeValues[cardNumInt-1];
		
		String expDate = request.getParameter("expDate");
		
		return null;
	}
	
	private boolean paymentInfoWasEntered(HttpServletRequest request) {
		String paymentInfoQuestionValue = request.getParameter("inlineRadioOptions");
		
		if(paymentInfoQuestionValue.equals("option1")) // Yes was chosen
			return true;
		
		return false; // Otherwise, No was chosen
	}
}
