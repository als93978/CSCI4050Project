package models;

public class User {
	private int userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private UserStatus status;
	private boolean enrollmentForPromotions;
	private int numOfCards;
	private UserType type;
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
	
	public UserStatus getStatus() {
		return status;
	}
	
	public void setStatusID(UserStatus status) {
		this.status = status;
	}
	
	public boolean getEnrollmentForPromotions() {
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
	
	public UserType getType() {
		return type;
	}
	
	public void setType(UserType type) {
		this.type = type;
	}
	
	public int getAddressID() {
		return addressID;
	}
	
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
}
