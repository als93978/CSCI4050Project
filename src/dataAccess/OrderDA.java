package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Order;

public class OrderDA implements IOrderDA {

	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String createOrderQuery = "INSERT INTO `Order`(UserID) "
												 + "VALUES(?)";
	
	private static final String getOrderByUserIDQuery = "SELECT * FROM `Order` "
													  + "WHERE UserID = ?;";
	
	@Override
	public void createOrder(Order order) throws SQLException {
		int userID = order.getUserID();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement createOrderStmt = connection.prepareStatement(createOrderQuery);
		createOrderStmt.setInt(1, userID);
		
		createOrderStmt.executeUpdate();
		
		connection.close();
	}

	@Override
	public Order getOrderByUserID(int userID) throws SQLException {
		Order order = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getOrderByUserIDStmt = connection.prepareStatement(getOrderByUserIDQuery);
		getOrderByUserIDStmt.setInt(1, userID);
		
		ResultSet orderRS = getOrderByUserIDStmt.executeQuery();
		
		while(orderRS.next()) {
			order = new Order();
			
			int orderID = orderRS.getInt(1);
			int dbUserID = orderRS.getInt(2);
			float totalPrice = orderRS.getFloat(3);
			String orderDateTime = orderRS.getString(4);
			String paymentMethod = orderRS.getString(5);
			String cardNum = orderRS.getString(6);
			int promotionCode = orderRS.getInt(7);
			
			order.setOrderID(orderID);
			order.setUserID(dbUserID);
			order.setTotalPrice(totalPrice);
			order.setOrderDateTime(orderDateTime);
			order.setPaymentMethod(paymentMethod);
			order.setCardNum(cardNum);
			order.setPromotionCode(promotionCode);
			
			connection.close();
			
			return order;
		}
		
		connection.close();
		
		return order;
	}

}
