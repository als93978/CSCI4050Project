package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.UserDA;
import models.Email;
import models.ErrorMessage;
import models.Message;
import models.User;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDA userDA = new UserDA();
	
	private User user = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
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
			checkEmailInput(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage());
		}
	}
	
	private void checkEmailInput(HttpServletRequest request, HttpServletResponse response) throws SQLException, MessagingException {
		String inputEmail = request.getParameter("email");
		
		if(inputEmail.equals(null) || inputEmail.equals("") || inputEmail.indexOf('@') == -1) {
			String invalidEmailMsg = "The email you entered is invalid. Please try again.";
			
			returnError(request, response, invalidEmailMsg);
		}
		
		else {
			sendRecoveryEmail(request, response, inputEmail);
		}
	}
	
	private void sendRecoveryEmail(HttpServletRequest request, HttpServletResponse response, String inputEmail) throws SQLException, MessagingException {
		user = userDA.getUserByEmail(inputEmail);
		
		if(user == null) {
			String invalidEmailMsg = "The email you entered is invalid or does belong to a user. Please try again.";
			
			returnError(request, response, invalidEmailMsg);
		}
		
		String confirmationCode = ConfirmUser.generateConfirmationCode();
		
		user.setConfirmationCode(confirmationCode);
		userDA.updateUser(user);
		
		String fromAddress = EmailHelper.fromAddress;
		String userEmail = user.getEmail();
		
		Email email = new Email();
		
		email.setFromAddress(fromAddress);
		email.setToAddress(userEmail);
		email.setBody(email.getRecoveryBody() + "?code=" + confirmationCode + "&email=" + userEmail);
		
		EmailHelper emailHelper = new EmailHelper();
		emailHelper.sendConfirmationEmail(email);
		
		String sentRecoveryMsg = "The recovery email has been sent. Please check your email.";
		returnMessage(request, response, sentRecoveryMsg);
	}
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "forgetPassword.jsp");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "forgetPassword.jsp");
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
