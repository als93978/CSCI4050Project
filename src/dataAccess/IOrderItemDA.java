package dataAccess;

import java.sql.SQLException;

import models.OrderItem;

public interface IOrderItemDA {
	public void createOrderItem(OrderItem orderItem) throws SQLException;
	
	public OrderItem getOrderItem(int bookID, int orderID) throws SQLException;
	
	public void updateOrderItem(OrderItem orderItem) throws SQLException;
}
