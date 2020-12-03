package models;

public class Email {
	
	private String fromAddress;
	private String toAddress;
	private String subject = "BookBay Account Confirmation";
	private String confirmationLink = "http://localhost:8080/CSCI4050Project/registrationConfirmation.jsp";
	private String confirmationBody = "Welcome to BookBay! Please confirm your"
			+ " account by entering the following confirmation code"
			+ " at " + confirmationLink + ": ";
	private String recoveryLink = "http://localhost:8080/CSCI4050Project/ResetPassword";
	private String recoveryBody = "You have requested to reset your password. To do so,"
			+ "go to the following link: " + recoveryLink;
	private String body = "";
	
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
	
	public String getConfirmationBody() {
		return confirmationBody;
	}
	
	public void setConfirmationBody(String confirmationBody) {
		this.confirmationBody = confirmationBody;
	}
	
	public String getRecoveryBody() {
		return recoveryBody;
	}
	
	public void setRecoveryBody(String recoveryBody) {
		this.recoveryBody = recoveryBody;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
}
