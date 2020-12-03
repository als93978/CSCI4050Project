package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.UserDA;
import models.ErrorMessage;
import models.Message;
import models.User;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDA userDA = new UserDA();
	
	private User user = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
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
			verifyRecoveryCode(request, response);
			changeToTemporaryPassword(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage());
		}
	}
	
	private void verifyRecoveryCode(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String recoveryCode = request.getParameter("code");
		String userEmail = request.getParameter("email");
		
		user = userDA.getUserByEmail(userEmail);
		
		if(recoveryCode == null || user == null) {
			String recoveryOrEmailInvalidMsg = "The recovery code or email was invalid. User not found.";
			
			returnError(request, response, recoveryOrEmailInvalidMsg);
		}
		
		if(!recoveryCode.equals(user.getConfirmationCode())) {
			String recoveryOrEmailInvalidMsg = "Recovery code does not match database. User not found.";
			
			returnError(request, response, recoveryOrEmailInvalidMsg);
		}
	}
	
	private void changeToTemporaryPassword(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		if(user != null) {
			String newTempPassword = CryptoHelper.generateRandomPassword(25);
			
			user.setPassword(newTempPassword);
			
			userDA.updatePassword(user);
			
			String newTempPasswordMsg = "Your password has been changed to a temporary password. "
					+ "Your new temporary password is: " + newTempPassword + ". You can now login and "
					+ "change your password.";
			returnMessage(request, response, newTempPasswordMsg);
		}
	}
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "login.jsp");
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
