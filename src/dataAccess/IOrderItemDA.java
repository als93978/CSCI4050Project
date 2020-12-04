package dataAccess;

import java.sql.SQLException;
import java.util.List;

import models.OrderItem;

public interface IOrderItemDA {
	public void createOrderItem(OrderItem orderItem) throws SQLException;
	
	public List<OrderItem> getOrderItemsByOrderID(int orderID) throws SQLException;
	
	public OrderItem getOrderItem(int bookID, int orderID) throws SQLException;
	
	public void updateOrderItem(OrderItem orderItem) throws SQLException;
	
	public void deleteOrderItem(OrderItem orderItem) throws SQLException;
}
