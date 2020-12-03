package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Order;
import models.OrderItem;

public class OrderItemDA implements IOrderItemDA {

	private static final String useDBQuery = "USE BookBayDB";
	
	private static final String createOrderItemQuery = "INSERT INTO OrderItem(BookID, OrderID, Quantity) "
													 + "VALUES(?, ?, ?);";
	
	private static final String getOrderItemsByOrderIDQuery = "SELECT * FROM OrderItem "
												            + "WHERE OrderID = ?;";
	

	private static final String getOrderItemQuery = "SELECT * FROM OrderItem "
												  + "WHERE BookID = ? "
												  + "and OrderID = ?;";
	
	private static final String updateOrderItemQuery = "UPDATE OrderItem "
													 + "SET Quantity = ? "
													 + "WHERE BookID = ? "
													 + "and OrderID = ?;";
	
	@Override
	public void createOrderItem(OrderItem orderItem) throws SQLException {
		int bookID = orderItem.getBookID();
		int orderID = orderItem.getOrderID();
		int quantity = orderItem.getQuantity();
		
		System.out.println("orderID in createOrderItem(): " + orderID);
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement createOrderItemStmt = connection.prepareStatement(createOrderItemQuery);
		createOrderItemStmt.setInt(1, bookID);
		createOrderItemStmt.setInt(2, orderID);
		createOrderItemStmt.setInt(3, quantity);
		
		createOrderItemStmt.executeUpdate();
		
		connection.close();
	}
	
	@Override
	public List<OrderItem> getOrderItemsByOrderID(int orderID) throws SQLException {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getOrderItemsByOrderIDStmt = connection.prepareStatement(getOrderItemsByOrderIDQuery);
		getOrderItemsByOrderIDStmt.setInt(1, orderID);
		
		ResultSet orderItemsRS = getOrderItemsByOrderIDStmt.executeQuery();
		
		while(orderItemsRS.next()) {
			OrderItem orderItem = new OrderItem();
			
			int bookID = orderItemsRS.getInt(1);
			int dbOrderID = orderItemsRS.getInt(2);
			int quantity = orderItemsRS.getInt(3);
			
			orderItem.setBookID(bookID);
			orderItem.setOrderID(dbOrderID);
			orderItem.setQuantity(quantity);
			
			orderItems.add(orderItem);
		}
		
		connection.close();
		
		return orderItems;
	}
	
	@Override
	public OrderItem getOrderItem(int bookID, int orderID) throws SQLException {
		OrderItem orderItem = null;
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement getOrderItemStmt = connection.prepareStatement(getOrderItemQuery);
		getOrderItemStmt.setInt(1, bookID);
		getOrderItemStmt.setInt(2, orderID);
		
		ResultSet orderItemRS = getOrderItemStmt.executeQuery();
		
		while(orderItemRS.next()) {
			orderItem = new OrderItem();
			
			int dbBookID = orderItemRS.getInt(1);
			int dbOrderID = orderItemRS.getInt(2);
			int quantity = orderItemRS.getInt(3);
			
			orderItem.setBookID(dbBookID);
			orderItem.setOrderID(dbOrderID);
			orderItem.setQuantity(quantity);
			
			connection.close();
			
			return orderItem;
		}
		
		connection.close();
		
		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) throws SQLException {
		int bookID = orderItem.getBookID();
		int orderID = orderItem.getOrderID();
		int quantity = orderItem.getQuantity();
		
		Connection connection = DataAccessHelper.getConnection();
		
		PreparedStatement useDBStmt = connection.prepareStatement(useDBQuery);
		useDBStmt.executeQuery();
		
		PreparedStatement updateOrderItemStmt = connection.prepareStatement(updateOrderItemQuery);
		updateOrderItemStmt.setInt(1, quantity);
		updateOrderItemStmt.setInt(2, bookID);
		updateOrderItemStmt.setInt(3, orderID);
		
		updateOrderItemStmt.executeUpdate();
		
		connection.close();
	}

}
