package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.BookDA;
import dataAccess.OrderDA;
import dataAccess.OrderItemDA;
import dataAccess.ShoppingCartDA;
import dataAccess.UserDA;
import models.Book;
import models.ErrorMessage;
import models.Message;
import models.Order;
import models.OrderItem;
import models.OrderStatus;
import models.ShoppingCart;
import models.User;
import models.UserType;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderItemDA orderItemDA = new OrderItemDA();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCart() {
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
			int bookID = Integer.parseInt(request.getParameter("bookID"));
			int orderID = Integer.parseInt(request.getParameter("orderID"));
			orderItemDA.deleteOrderItem(orderItemDA.getOrderItem(bookID, orderID));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
