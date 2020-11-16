package models;

public class PaymentCard {
	private int cardNum;
	private CardType cardType;
	private String expDate;
	private int userID;
	
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
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
