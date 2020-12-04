package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import dataAccess.AddressDA;
import dataAccess.UserDA;
import models.Email;
import models.ErrorMessage;
import models.Message;
import models.PaymentCard;
import models.Address;
import models.CardType;
import models.User;

/**
 * Servlet implementation class SendAddress
 */
@WebServlet("/SendAddress")
public class SendAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AddressDA addressDA = new AddressDA();
	private UserDA userDA = new UserDA();
	
	private User user = null;
	private Address address = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendAddress() {
        super();
        // TODO Auto-generated constructor stub
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
			processAddress(request);
			
			String message = "Address was successfully added.";
			returnMessage(request, response, message);
			
		} catch(Exception e) {
			e.printStackTrace();
			returnError(request, response, e.getMessage());
		}
	}
	private void processAddress(HttpServletRequest request) throws Exception {
		address = initAddress(request);
		addressDA.createAddress(address);
		
		Cookie[] cookies = request.getCookies();
        int userID = Integer.parseInt(cookies[1].getValue());
		
		try {
		user = userDA.getUserByID(userID);
		}catch (Exception e) {
			e.printStackTrace();
		}
		address = addressDA.getLastAddress();
		user.setAddressID(address.getAddressID());
		userDA.updateUser(user);
	}
	
	private Address initAddress(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
        int userID = Integer.parseInt(cookies[1].getValue());
		
		try {
		user = userDA.getUserByID(userID);
		}catch (Exception e) {
			e.printStackTrace();
		}

		Address address = new Address();

		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zipCode = Integer.parseInt(request.getParameter("zipCode"));

		address.setStreet(street);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipCode);
		
		return address;
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
