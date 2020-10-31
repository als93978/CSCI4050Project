package models;

public class Email {
	
	private String fromAddress;
	private String toAddress;
	private String subject = "BookBay Account Confirmation";
	private String body = "Welcome to BookBay! Please confirm your"
			+ " account by entering the following confirmation code"
			+ " at http://localhost:8080/CSCI4050Project/registrationConfirmation.html: ";
	
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
