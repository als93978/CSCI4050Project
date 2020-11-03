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
 * Servlet implementation class UpdateCard
 */
@WebServlet("/UpdateCard")
public class UpdateCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public UpdateCard() {
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
		int userID = Integer.parseInt(request.getParameter("userID"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int cardNum = Integer.parseInt(request.getParameter("cardNum"));
        int cardSecurityNum = Integer.parseInt(request.getParameter("cardSecurityNum"));
        
        int cardTypeNum = Integer.parseInt(request.getParameter("cardType"));
        CardType[] cardTypeValues = CardType.values();
        CardType cardType = cardTypeValues[cardTypeNum-1];
        
        String expDate = request.getParameter("expDate");

        PaymentCardDA.editCardValue(userID, "firstName", firstName);
		PaymentCardDA.editCardValue(userID, "lastName", lastName);
		PaymentCardDA.editCardValue(userID, "cardNum", cardNum);
		PaymentCardDA.editCardValue(userID, "cardSecurityNum", cardSecurityNum);
		PaymentCardDA.editCardValue(userID, "cardType", cardType);
		PaymentCardDA.editCardValue(userID, "expDate", expDate);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/accountSettings.jsp");
        dispatcher.forward(request, response);
	}

}
