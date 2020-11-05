package controllers;

import java.io.IOException;

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
 * Servlet implementation class UpdateAddress
 */
@WebServlet("/UpdateAddress")
public class UpdateAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateAddress() {
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
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
	    	for(Cookie cookie : cookies) {
	    		if(cookie.getName().equals("userID")) {
	    			userCookie = cookie;
	    			break;
	    		}
	    	}
    	}
	int userID = userCookie.getValue();
	int addressID = UserDA.getUserValue("AddressID", "UserID", userID);
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        int zipCode = Integer.parseInt(request.getParameter("zipCode"));
        String country = request.getParameter("country");

        AddressDA.editAddressValue(addressID, "Street", street);
		AddressDA.editAddressValue(addressID, "City", city);
		AddressDA.editAddressValue(addressID, "State", state);
		AddressDA.editAddressValue(addressID, "ZipCode", zipCode);
		AddressDA.editAddressValue(addressID, "Country", country);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/accountSettings.jsp");
        dispatcher.forward(request, response);
	}

}
