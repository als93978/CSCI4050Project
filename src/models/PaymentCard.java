package models;

public class PaymentCard {
	private String firstName;
	private String lastName;
	private int cardNum;
	private int cardSecurityNum;
	private CardType cardType;
	private String expDate;
	private int userID;
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(){
		this.lastName = lastName;
	}
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	public int getCardSecurityNum(){
		return cardSecurityNum;
	}
	public void setCardSecurityNum(){
		this.cardSecurityNum = cardSecurityNum;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
