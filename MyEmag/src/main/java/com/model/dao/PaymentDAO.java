package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAO {
	

	private static PaymentDAO instance;
	
	private PaymentDAO(){
	}
	
	public static synchronized PaymentDAO getInstance(){
		if(instance == null){
			instance = new PaymentDAO();
		}
		return instance;
	}
	
	public synchronized void addpayment (String type) throws SQLException{
		String sql = "INSERT INTO payments (type) values (?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, type);	
		st.executeUpdate();
	}

	public synchronized int getPaymentId (String type) throws SQLException{
		String sql = "SELECT id FROM payments WHERE type=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, type);
		ResultSet rs=st.executeQuery();
		int paymentId=rs.getInt(1);
		return paymentId;
	}
	
	public synchronized ArrayList<String> getAllPayments () throws SQLException {
		ArrayList<String> payments=new ArrayList<>();
		String sql = "SELECT type FROM payments";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			String payment=rs.getString("type");
			payments.add(payment);
		}
		return payments;
	}
}
