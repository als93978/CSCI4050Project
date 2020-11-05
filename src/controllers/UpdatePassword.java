package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.AddressDA;
import dataAccess.PaymentCardDA;
import dataAccess.UserDA;
import models.Address;
import models.CardType;
import models.PaymentCard;
import models.UserStatus;
import models.UserType;
import models.User;

/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdatePassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/accountSettings.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie userCookie = null;
	Cookie[] = cookies = request.getCookies();
	if (cookies != null) {
	    for(Cookie cookie : cookies) {
	    	if(cookie.getName().equals("userID")) {
	    		userCookie = cookie;
	    		break;
	    	}
	    }
    	}
	int userID = userCookie.getValue();
        String password = request.getParameter("password");

        try {
			UserDA.editUserValue(userID, "Password", password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/accountSettings.jsp");
        dispatcher.forward(request, response);
	}

}
