package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.UserDA;
import models.ErrorMessage;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
			String userID = validateLoginInformation(request, response);
			setSessionCookie(request, response, userID);
		} catch(Exception e) {
			interpretAndReturnException(request, response, e);
		}
	}
	
	private String validateLoginInformation(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String emailAccountID = request.getParameter("emailAccountID");
		String inputPassword = request.getParameter("password");
		
		boolean isAccountID = isAccountID(emailAccountID);
		
		String userID = "";
		
		if(isAccountID) {
			userID = emailAccountID;
			
			String dbPassword = UserDA.getUserValue("`Password`", "UserID", userID);
			
			checkPassword(request, response, inputPassword, dbPassword);
		}
		
		else {
			String email = emailAccountID;
			
			userID = String.valueOf((int) UserDA.getUserValue("UserID", "Email", email));
			
			String dbPassword = UserDA.getUserValue("`Password`", "UserID", userID);
			
			checkPassword(request, response, inputPassword, dbPassword);
		}
		
		return userID;
	}
	
	private void setSessionCookie(HttpServletRequest request, HttpServletResponse response, String userID) throws IOException {
		Cookie sessionCookie = new Cookie("userID", userID);
		
		response.addCookie(sessionCookie);
		
		response.sendRedirect(request.getContextPath() + "/Index");
	}
	
	private boolean isAccountID(String emailAccountID) {
		int indexOfEmailAccountID = emailAccountID.indexOf('@');
		
		if(indexOfEmailAccountID == -1)
			return true;
		
		return false;
	}
	
	private void interpretAndReturnException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		returnError(request, response, e.getMessage());
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
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
	
	private void checkPassword(HttpServletRequest request, HttpServletResponse response, String inputPassword, String dbPassword) {
		if(!inputPassword.equals(dbPassword)) {
			String wrongLoginMsg = "The email/account ID and/or password you entered was incorrect.";
			
			returnError(request, response, wrongLoginMsg);
		}
	}

}
