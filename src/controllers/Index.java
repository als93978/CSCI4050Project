package controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.UserDA;
import models.User;
import models.UserType;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDA userDA = new UserDA();
	
	private User user = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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
	
	private Cookie checkUserLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			if(cookies.length > 1) {
				if(cookies[1].getName().equals("userID")) {
					processSessionCookie(request, response, cookies[1]);
				}
				
				else {
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			}
			
			else {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
		
		return null;
	}
	
	private void processSessionCookie(HttpServletRequest request, HttpServletResponse response, Cookie sessionCookie) throws SQLException, IOException {
		int userID = Integer.parseInt(sessionCookie.getValue());
		
		user = userDA.getUserByID(userID);
		
		boolean isUserAdmin = checkUserIsAdmin();
		
		if(isUserAdmin) {
			response.sendRedirect(request.getContextPath() + "/ManageBooks");
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/homepageWithUserIcon.html");
		}
	}
	
	private boolean checkUserIsAdmin() throws SQLException {
		UserType userType = user.getType();
		
		if(userType.equals(UserType.ADMIN))
			return true;
		
		return false;
	}

}
