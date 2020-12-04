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
 * Servlet implementation class SCUpdateAddress
 */
@WebServlet("/SCUpdateAddress")
public class SCUpdateAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDA userDA = new UserDA();
	private AddressDA addressDA = new AddressDA();
	
	private User user = null;
	private Address address = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SCUpdateAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cookie[] cookies = request.getCookies();
	        int userID = Integer.parseInt(cookies[1].getValue());
	        
	        user = userDA.getUserByID(userID);
	        
	        String street = request.getParameter("street");
	        String city = request.getParameter("city");
	        String state = request.getParameter("state");
	        int zipCode = Integer.parseInt(request.getParameter("zipCode"));
	        
	        int addressID = user.getAddressID();
	        
	        address = addressDA.getAddressByID(addressID);
	        
	        if(address == null) {
	        	address = new Address();
	        	
	        	address.setStreet(street);
	        	address.setCity(city);
	        	address.setState(state);
	        	address.setZipCode(zipCode);
	        	
	        	addressDA.createAddress(address);
	        	
	        	address = addressDA.getLastAddress();
	        	
	        	user.setAddressID(address.getAddressID());
	        	userDA.updateUser(user);
	        }
	        
	        else {
	        	address.setStreet(street);
	        	address.setCity(city);
	        	address.setState(state);
	        	address.setZipCode(zipCode);
	        	
	        	addressDA.updateAddress(address);
	        }
	
	        String message = "Address changes saved.";
	        returnMessage(request, response, message);
		} catch(Exception e) {
			e.printStackTrace();
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
		
		redirectToPage(request, response, "shoppingCart.jsp");
	}
	
	private void returnError(HttpServletRequest request, HttpServletResponse response, String message) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + message);
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "shoppingCart.jsp");
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
