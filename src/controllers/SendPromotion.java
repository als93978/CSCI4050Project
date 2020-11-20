package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

import dataAccess.PromotionDA;
import dataAccess.UserDA;
import models.Email;
import models.ErrorMessage;
import models.Promotion;
import models.User;
import models.UserStatus;
import models.UserType;

/**
 * Servlet implementation class SendPromotion
 */
@WebServlet("/SendPromotion")
public class SendPromotion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PromotionDA promotionDA = new PromotionDA();
	private UserDA userDA = new UserDA();
	
	private Promotion promotion = null;
	private User user = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendPromotion() {
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
			processPromotion(request);
			sendPromotionEmail(request);
			redirectToPage(request, response, "adminManagePromotions.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			interpretAndReturnException(request, response, e);
		}
	}
	
	private void processPromotion(HttpServletRequest request) throws Exception {
		promotion = initPromotion(request);
		promotionDA.createPromotion(promotion);
	}
	
	private Promotion initPromotion(HttpServletRequest request) {
		Promotion promotion = new Promotion();
		
		int promotionCode = Integer.parseInt(request.getParameter("promotionCode"));
		int percentage = Integer.parseInt(request.getParameter("percentage"));
		String expDate = request.getParameter("expDate");
		String startDate = request.getParameter("startDate");
		
		promotion.setPromotionCode(promotionCode);
		promotion.setPercentage(percentage);
		promotion.setExpDate(expDate);
		promotion.setStartDate(startDate);
		
		return promotion;
	}
	
	private void sendPromotionEmail(HttpServletRequest request) throws SQLException, MessagingException {
		promotion = promotionDA.getLastPromotion();
		List<User> allUsers = userDA.getAllUsers();
		
		String fromAddress = EmailHelper.fromAddress;
		
		for (int i = 0; i < allUsers.size(); i++) {
			String userEmail = allUsers.get(i).getEmail();
			Email email = new Email();
			email.setFromAddress(fromAddress);
			email.setToAddress(userEmail);
			email.setBody(email.getBody() + "Promotion Code: " + promotion.getPromotionCode() + "\n" + promotion.getPercentage()
				+ "% off any purchase\n" + "Expiration Date: " + promotion.getExpDate()
				+ "\nStart Date: " + promotion.getStartDate());
			EmailHelper emailHelper = new EmailHelper();
			emailHelper.sendConfirmationEmail(email);
		}
	}
	
	private void interpretAndReturnException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setMessage("An error occurred: " + e.getMessage());
		
		request.setAttribute("errorMessage", errorMessage);
		
		redirectToPage(request, response, "registration.jsp");
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
