package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Order;
import models.OrderStatus;

public class OrderDA implements IOrderDA {

	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String createOrderQuery = "INSERT INTO `Order`(UserID, OrderStatus) "
												 + "VALUES(?, ?)";
	
	private static final String getNonSubmittedOrderByUserIDQuery = "SELECT * FROM `Order` "
													  + "WHERE UserID = ? "
													  + "and OrderStatus = ? "
													  + "ORDER BY OrderID DESC LIMIT 1;";
	
	@Override
	public void createOrder(Order order) throws SQLException {
		int userID = order.getUserID();
		String orderStatus = order.getOrderStatus().name();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement createOrderStmt = connection.prepareStatement(createOrderQuery);
		createOrderStmt.setInt(1, userID);
		createOrderStmt.setString(2, orderStatus);
		
		createOrderStmt.executeUpdate();
		
		connection.close();
	}

	@Override
	public Order getNonSubmittedOrderByUserID(int userID) throws SQLException {
		Order order = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getNonSubmittedOrderByUserIDStmt = connection.prepareStatement(getNonSubmittedOrderByUserIDQuery);
		getNonSubmittedOrderByUserIDStmt.setInt(1, userID);
		getNonSubmittedOrderByUserIDStmt.setString(2, "NOTSUBMITTED");
		
		ResultSet orderRS = getNonSubmittedOrderByUserIDStmt.executeQuery();
		
		while(orderRS.next()) {
			order = new Order();
			
			int orderID = orderRS.getInt(1);
			int dbUserID = orderRS.getInt(2);
			float totalPrice = orderRS.getFloat(3);
			String orderDateTime = orderRS.getString(4);
			String paymentMethod = orderRS.getString(5);
			String cardNum = orderRS.getString(6);
			int promotionCode = orderRS.getInt(7);
			OrderStatus orderStatus = OrderStatus.valueOf(orderRS.getString(8));
			
			order.setOrderID(orderID);
			order.setUserID(dbUserID);
			order.setTotalPrice(totalPrice);
			order.setOrderDateTime(orderDateTime);
			order.setPaymentMethod(paymentMethod);
			order.setCardNum(cardNum);
			order.setPromotionCode(promotionCode);
			order.setOrderStatus(orderStatus);
			
			connection.close();
			
			return order;
		}
		
		connection.close();
		
		return order;
	}

}
