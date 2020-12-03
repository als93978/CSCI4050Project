package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import models.UserType;

/**
 * Servlet implementation class ManageUsers
 */
@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDA userDA = new UserDA();
	
	private User user = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUsers() {
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
				user = userDA.getUserByID(Integer.parseInt(cookies[1].getValue()));
				
				processSessionCookie(request, response, cookies[1]);
			}
			
			else {
				String mustBeLoggedInMsg = "You must be logged in to access this page.";
				
				returnError(request, response, mustBeLoggedInMsg, "login.jsp");			
			}
		}
		
		else {
			String mustBeLoggedInMsg = "You must be logged in to access this page.";
			
			returnError(request, response, mustBeLoggedInMsg, "login.jsp");
		}
	}
	
	private void processSessionCookie(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) throws NumberFormatException, Exception {
		boolean isUserAdmin = checkUserIsAdmin();
		
		if(isUserAdmin) {
			loadAllUsersInfo(request, response);		
		}
		
		else {
			String isNotAdminMsg = "You do not have permission to view this page.";
			
			returnError(request, response, isNotAdminMsg, "login.jsp");	
		}
	}
	
	private void loadAllUsersInfo(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		List<User> customers = userDA.getAllCustomerUsers();
		List<User> admins = userDA.getAllAdminUsers();
		List<User> employees = userDA.getAllEmployeeUsers();
		
		if(customers != null) {
			request.setAttribute("customers", customers);
		}
		
		if(admins != null) {
			request.setAttribute("admins", admins);
		}
		
		if(employees != null) {
			request.setAttribute("employees", employees);
		}
		
		redirectToPage(request, response, "adminManageUsers.jsp");
	}
	
	private boolean checkUserIsAdmin() throws SQLException {
		UserType userType = user.getType();
		
		if(userType.equals(UserType.ADMIN))
			return true;
		
		return false;
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message, String redirectTo) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, redirectTo);
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
