package models;

public class User {
	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int statusID;
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
	
	public int getStatusID() {
		return statusID;
	}
	
	public void setStatusID(int statusID) {
		this.statusID = statusID;
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
}
