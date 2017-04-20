package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public synchronized ArrayList<String> getAllCategories () throws SQLException {
		ArrayList<String> categories=new ArrayList<>();
		String sql = "SELECT name FROM categories";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			String category=rs.getString("name");
			categories.add(category);
		}
		return categories;
	}	
}
