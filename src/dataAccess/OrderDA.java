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
	
	private static final String updateOrderQuery = "UPDATE `Order` "
												 + "SET UserID = ?, "
												 + "TotalPrice = ?, "
												 + "OrderDateTime = ?, "
												 + "PaymentMethod = ?, "
												 + "CardNum = ?, "
												 + "AddressID = ?, "
												 + "PromotionCode = ?, "
												 + "OrderStatus = ? "
												 + "WHERE OrderID = ?;";
	
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
			int addressID = orderRS.getInt(7);
			int promotionCode = orderRS.getInt(8);
			OrderStatus orderStatus = OrderStatus.valueOf(orderRS.getString(9));
			
			order.setOrderID(orderID);
			order.setUserID(dbUserID);
			order.setTotalPrice(totalPrice);
			order.setOrderDateTime(orderDateTime);
			order.setPaymentMethod(paymentMethod);
			order.setCardNum(cardNum);
			order.setAddressID(addressID);
			order.setPromotionCode(promotionCode);
			order.setOrderStatus(orderStatus);
			
			connection.close();
			
			return order;
		}
		
		connection.close();
		
		return order;
	}

	@Override
	public void updateOrder(Order order) throws SQLException {
		int orderID = order.getOrderID();
		int userID = order.getUserID();
		float totalPrice = order.getTotalPrice();
		String orderDateTime = order.getOrderDateTime();
		String paymentMethod = order.getPaymentMethod();
		String cardNum = order.getCardNum();
		int addressID = order.getAddressID();
		int promotionCode = order.getPromotionCode();
		String orderStatus = order.getOrderStatus().name();
	
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updateOrderStmt = connection.prepareStatement(updateOrderQuery);
		updateOrderStmt.setInt(1, userID);
		updateOrderStmt.setFloat(2, totalPrice);
		updateOrderStmt.setString(3, orderDateTime);
		updateOrderStmt.setString(4, paymentMethod);
		updateOrderStmt.setString(5, cardNum);
		
		if(addressID == 0)
			updateOrderStmt.setString(6, null);
		else
			updateOrderStmt.setInt(6, addressID);
		
		if(promotionCode == 0)
			updateOrderStmt.setString(7, null);
		else
			updateOrderStmt.setInt(7, promotionCode);
		
		updateOrderStmt.setString(8, orderStatus);
		updateOrderStmt.setInt(9, orderID);

		updateOrderStmt.executeUpdate();
		
		connection.close();
	}

}
