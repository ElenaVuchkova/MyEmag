package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubcategoryDAO {

	private static SubcategoryDAO instance;
	
	private SubcategoryDAO(){}
	
	public static synchronized SubcategoryDAO getInstance(){
		if(instance == null){
			instance = new SubcategoryDAO();
		}
		return instance;
	}
	
	public synchronized void addSubcategory (String categoryName, String subcategoryName) throws SQLException{
		int categoryId=CategoryDAO.getInstance().getCategoryId(categoryName);
		String sql = "INSERT INTO subcategories (name, category_id) values (?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, subcategoryName);
		st.setInt(2, categoryId);
		st.executeUpdate();
	}
	
	public synchronized int getSubcategoryId (String name) throws SQLException {
		String sql = "SELECT subcategory_id FROM subcategories WHERE name=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs=st.executeQuery();
		rs.next();
		int subcategoryId=rs.getInt(1);
		return subcategoryId;
	}
	
	public synchronized ArrayList<String> getAllSubcategoryByCategory (String name) throws SQLException {
		ArrayList<String> subcategories=new ArrayList<>();
		String sql = "SELECT s.name FROM subcategories s JOIN categories c ON (s.category_id=c.category_id) where c.name=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs=st.executeQuery();
		System.out.println("test before while +++++++++++++++++++++++++++++++++++++");
		while (rs.next()) {			
			String subcategory=rs.getString("name");
			subcategories.add(subcategory);
			System.out.println("in while +++++++++++++++++++++++++++++++++++++");
			System.out.println(subcategory);
		}
		return subcategories;
	}
 }
