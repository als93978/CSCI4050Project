package models;

public class Promotion {
	private int promotionCode;
	private int percentage;
	private String expDate;
	private String startDate;
	
	public int getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(int promotionCode) {
		this.promotionCode = promotionCode;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
