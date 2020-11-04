package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.UserDA;
import models.ErrorMessage;
import models.Message;
import models.UserStatus;

/**
 * Servlet implementation class ConfirmUser
 */
@WebServlet("/ConfirmUser")
public class ConfirmUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmUser() {
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
			checkConfirmationCode(request, response);
		} catch(Exception e) {
			e.printStackTrace();
//			interpretAndReturnException(request, response, e);
		}
	}
	
	private void checkConfirmationCode(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String inputEmail = request.getParameter("email");
		String inputConfirmationCode = request.getParameter("code");
		
		String dbConfirmationCode = UserDA.getUserValue("ConfirmationCode", "Email", inputEmail);
		
		if(inputConfirmationCode.equals(dbConfirmationCode)) {
			int userID = UserDA.getUserValue("UserID", "Email", inputEmail);
			
			UserDA.editUserValue(userID, "Status", UserStatus.ACTIVE.name());
			
			String message = "You have successfully confirmed your registration. You can now login.";
			returnMessage(request, response, message);
		}
		
		else {
			String wrongConfirmationCodeMsg = "The confirmation code and/or the email address you entered was incorrect.";
			
			returnError(request, response, wrongConfirmationCodeMsg);
		}
	}
	
	private void interpretAndReturnException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		returnError(request, response, e.getMessage());
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "registrationConfirmation.jsp");
	}
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "login.jsp");
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
	
	public static String generateConfirmationCode() {
		String code = "";
		
		Random random = new Random();
		for(int i = 0; i < 4; i++) // Generates 4-digit random number code
			code += String.valueOf(random.nextInt(10));
		
		return code;
	}

}
