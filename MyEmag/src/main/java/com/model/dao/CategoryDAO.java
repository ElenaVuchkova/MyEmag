package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Product;

public class CategoryDAO {

	private static CategoryDAO instance;
	
	private CategoryDAO(){
	}
	
	public static synchronized CategoryDAO getInstance(){
		if(instance == null){
			instance = new CategoryDAO();
		}
		return instance;
	}

	public synchronized void addCategory (String name) throws SQLException{
		String sql = "INSERT INTO categories (name) values (?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, name);	
		st.executeUpdate();
	}
	
	public synchronized int getCategoryId (String name) throws SQLException {
		String sql = "SELECT category_id FROM categories WHERE name=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs=st.executeQuery();
		int categoryId=rs.getInt(1);
		return categoryId;
	}
}
