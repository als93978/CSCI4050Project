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

    private AddressDA addressDA = new AddressDA();
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
		int addressID = request.getParameter("addressID");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        int zipCode = request.getParameter("zipCode");
        String country = request.getParameter("country");

        try{
            addressDA.editAddressValue(addressID, "Street", street);
            addressDA.editAddressValue(addressID, "City", city);
            addressDA.editAddressValue(addressID, "State", state);
            addressID.editAddressValue(addressID, "ZipCode", zipCode);
            addressID.editAddressValue(addressID, "Country", country);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/accountSettings.jsp");
        dispatcher.forward(request, response);
	}

}
