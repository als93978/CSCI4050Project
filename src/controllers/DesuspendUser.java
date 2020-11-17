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
import models.User;
import models.UserStatus;

/**
 * Servlet implementation class DesuspendUser
 */
@WebServlet("/DesuspendUser")
public class DesuspendUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDA userDA = new UserDA();
	
	private User user = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DesuspendUser() {
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
			desuspendUser(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void desuspendUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		
		user = userDA.getUserByID(userID);
		
		// **********************************
		// !!!!!! NOTE: This sets UserStatus to ACTIVE, but it could have been INACTIVE.
		// Leaving this for now, but it needs to be fixed at some point.
		// **********************************
		user.setStatusID(UserStatus.ACTIVE);
		userDA.updateUser(user);
		
		redirectToPage(request, response, "ManageUsers");
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
