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

    private PaymentCardDA paymentCardDA = new PaymentCardDA();
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
		int userID = request.getParameter("userID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int cardNum = request.getParameter("cardNum");
        int cardSecurityNum = request.getParameter("cardSecurityNum");
        CardType cardType = request.getParameter("cardType");
        String expDate = request.getParameter("expDate");

        try{
            paymentCardDA.editCardValue(userID, "firstName", firstName);
            paymentCardDA.editCardValue(userID, "lastName", lastName);
            paymentCardDA.editCardValue(userID, "cardNum", cardNum);
            paymentCardDA.editCardValue(userID, "cardSecurityNum", cardSecurityNum);
            paymentCardDA.editCardValue(userID, "cardType", cardType);
            paymentCardDA.editCardValue(userID, "expDate", expDate);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/accountSettings.jsp");
        dispatcher.forward(request, response);
	}

}
