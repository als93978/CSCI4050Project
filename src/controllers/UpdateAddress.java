package controllers;

import java.io.IOException;

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
import models.CardType;
import models.ErrorMessage;
import models.Message;
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cookie[] cookies = request.getCookies();
	        String userID = cookies[1].getValue();
	        
	        String street = request.getParameter("street");
	        String city = request.getParameter("city");
	        String state = request.getParameter("state");
	        int zipCode = Integer.parseInt(request.getParameter("zipCode"));
	        
	        String addressID = UserDA.getUserValue("AddressID", "UserID", userID);
	        
	        if(addressID == null) {
	        	Address address = new Address();
	        	address.setStreet(street);
	        	address.setCity(city);
	        	address.setState(state);
	        	address.setZipCode(zipCode);
	        	
	        	AddressDA.addAddressToDB(address);
	        	
	        	UserDA.editUserValue(Integer.parseInt(userID), "AddressID", "1");
	        }
	        
	        else {
	        	AddressDA.editAddressValue(Integer.parseInt(addressID), "Street", street);
	        	AddressDA.editAddressValue(Integer.parseInt(addressID), "City", city);
	        	AddressDA.editAddressValue(Integer.parseInt(addressID), "State", state);
	        	AddressDA.editAddressValue(Integer.parseInt(addressID), "ZipCode", zipCode);
	        }
	
	        String message = "Address changes saved.";
	        returnMessage(request, response, message);
		} catch(Exception e) {
			//e.printStackTrace();
			interpretAndReturnException(request, response, e);
		}
	}
	
	private void interpretAndReturnException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		returnError(request, response, e.getMessage());
	}
	
	private void returnMessage(HttpServletRequest request, HttpServletResponse response, String messageStr) {
		Message message = new Message();
		
		message.setMessage(messageStr);
		
		request.setAttribute("message", message);
		
		redirectToPage(request, response, "accountSettings.jsp");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "accountSettings.jsp");
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
