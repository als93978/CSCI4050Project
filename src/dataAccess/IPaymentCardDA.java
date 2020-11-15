package dataAccess;

import java.sql.SQLException;

import models.PaymentCard;

public interface IPaymentCardDA {
	public void createPaymentCard(PaymentCard paymentCard) throws SQLException;
	
	public PaymentCard getPaymentCardByUserID(int userID) throws SQLException;
	
	public PaymentCard getPaymentCardByCardNum(String cardNum) throws SQLException;
	
	public PaymentCard getLastPaymentCard() throws SQLException;
	
	public void updatePaymentCard(PaymentCard paymentCard) throws SQLException;
	
	public void updateCardNum(PaymentCard paymentCard) throws SQLException;
	
	public void deletePaymentCard(PaymentCard paymentCard) throws SQLException;
}
