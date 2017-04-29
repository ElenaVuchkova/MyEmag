package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderHasProductDAO {
	
	private static OrderHasProductDAO instance;
	
	private OrderHasProductDAO(){
	}
	
	public static synchronized OrderHasProductDAO getInstance(){
		if(instance == null){
			instance = new OrderHasProductDAO();
		}
		return instance;
	}
	
	public synchronized void addOrderedProduct (int orderId, int productId, int quantity) throws SQLException{
		String sql = "INSERT INTO orders_has_products (order_id, product_id, quantity) values (?,?,?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setInt(1, orderId);
		st.setInt(2, productId);
		st.setInt(3, quantity);
		st.executeUpdate();
	}

}
