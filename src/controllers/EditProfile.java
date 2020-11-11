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

import dataAccess.AddressDA;
import dataAccess.PaymentCardDA;
import dataAccess.UserDA;
import models.Address;
import models.ErrorMessage;
import models.PaymentCard;
import models.User;
import models.UserType;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
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
			checkUserLoggedIn(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void checkUserLoggedIn(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Cookie[] cookies = request.getCookies();
		
		if(cookies.length > 1) {
			if(cookies[1].getName().equals("userID")) {
				processSessionCookie(request, response, cookies[1]);
			}
			
			else {
				String mustBeLoggedInMsg = "You must be logged in to access this page.";
				
				returnError(request, response, mustBeLoggedInMsg);			
			}
		}
		
		else {
			String mustBeLoggedInMsg = "You must be logged in to access this page.";
			
			returnError(request, response, mustBeLoggedInMsg);			
		}
	}
	
	private void loadExistingUserInformation(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) throws NumberFormatException, Exception {
		String userID = sessionCookie.getValue();
		
		User user = UserDA.getUser(Integer.parseInt(userID));
		
		Integer addressID = UserDA.getUserValue("AddressID", "UserID", userID);
		
		Address address = null;
		if(addressID != null)
			address = AddressDA.getAddress(addressID);
		
		PaymentCard paymentCard = PaymentCardDA.getPaymentCard(Integer.parseInt(userID));
		
		request.setAttribute("user", user);
		
		if(address != null) {
			request.setAttribute("address", address);
		}
		
		if(paymentCard != null) {
			request.setAttribute("paymentCard", paymentCard);
		}
			
		redirectToPage(request, response, "accountSettings.jsp");
	}
	
	private void processSessionCookie(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) throws NumberFormatException, Exception {
		boolean isUserAdmin = checkUserIsAdmin(sessionCookie);
		
		if(isUserAdmin) {
			//response.sendRedirect(request.getContextPath() + "/adminManageBooks.html");
			redirectToPage(request, response, "adminManageBooks.html");
		}
		
		else {
			loadExistingUserInformation(request, response, sessionCookie);
		}
	}
	
	private boolean checkUserIsAdmin(Cookie sessionCookie) throws SQLException {
		String userID = sessionCookie.getValue();
		
		UserType userType = UserType.valueOf((String) UserDA.getUserValue("`Type`", "UserID", userID));
		
		if(userType.equals(UserType.ADMIN))
			return true;
		
		return false;
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

}
