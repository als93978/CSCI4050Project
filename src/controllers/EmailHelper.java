package controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.Email;

public class EmailHelper {
	
	public static String fromAddress = "bookbayauto@gmail.com";
	
	private String fromAddressPassword = "xn4#lWd7!eg8H4Ps@0eyBA7jB";
	private String host = "smtp.gmail.com";
	
	public void sendConfirmationEmail(Email email) throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(properties);
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromAddress));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getToAddress()));
		message.setSubject(email.getSubject());
		message.setText(email.getBody());
		
		Transport.send(message, fromAddress, fromAddressPassword);
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHostAddress(String host) {
		this.host = host;
	}
}
