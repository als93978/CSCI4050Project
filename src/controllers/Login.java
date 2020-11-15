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
import models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDA userDA = new UserDA();
	
	private User user = null;
       
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
			validateLoginInformation(request, response);
			setSessionCookie(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			interpretAndReturnException(request, response, e);
		}
	}
	
	private void validateLoginInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String emailAccountID = request.getParameter("emailAccountID");
		String inputPassword = request.getParameter("password");
		
		boolean isAccountID = isAccountID(emailAccountID);
		
		if(isAccountID) {
			int userID = Integer.parseInt(emailAccountID);
			
			user = userDA.getUserByID(userID);
			
			String dbPassword = user.getPassword();
			
			checkPassword(request, response, inputPassword, dbPassword);
		}
		
		else {
			String email = emailAccountID;
			
			user = userDA.getUserByEmail(email);
			
			String dbPassword = user.getPassword();
			
			checkPassword(request, response, inputPassword, dbPassword);
		}
	}
	
	private void setSessionCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie sessionCookie = new Cookie("userID", String.valueOf(user.getUserID()));
		
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
		System.out.println("inputPassword: " + inputPassword);
		System.out.println("dbPassword: " + dbPassword);
		
		if(!CryptoHelper.getPasswordEncryptor().checkPassword(inputPassword, dbPassword)) {
			String wrongLoginMsg = "The email/account ID and/or password you entered was incorrect.";
			
			returnError(request, response, wrongLoginMsg);
		}
	}

}
