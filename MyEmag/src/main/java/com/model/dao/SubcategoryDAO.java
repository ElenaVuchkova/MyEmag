package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model.User;

public class SubcategoryDAO {

	private static SubcategoryDAO instance;
	private static final  ArrayList<String> ALL_SUBCATEGORIES=new ArrayList<>();
	
	private SubcategoryDAO() throws SQLException{
		getAllSubcategories();
	}
	
	public static synchronized SubcategoryDAO getInstance() throws SQLException{
		if(instance == null){
			instance = new SubcategoryDAO();
		}
		return instance;
	}
	
	private ArrayList<String> getAllSubcategories() throws SQLException{
		if(ALL_SUBCATEGORIES.isEmpty()){
			String sql = "SELECT name FROM subcategories;";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				String subcategory=res.getString("name");
				ALL_SUBCATEGORIES.add(subcategory);
			}
		}
		return ALL_SUBCATEGORIES;
	}
	
	public synchronized void addSubcategory (int categoryId, String subcategoryName) throws SQLException{
		String sql = "INSERT INTO subcategories (name, category_id) values (?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, subcategoryName);
		st.setInt(2, categoryId);
		st.executeUpdate();
		ALL_SUBCATEGORIES.add(subcategoryName);
	}
	
	public synchronized boolean isSubcategory (String subcategory) {
		if (subcategory!=null) {
			for (String s: ALL_SUBCATEGORIES) {
				if (s.equals(subcategory)) {
					return true;
				}
			}
		}
		return false;
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
		while (rs.next()) {			
			String subcategory=rs.getString("name");
			subcategories.add(subcategory);
		}
		return subcategories;
	}
 }
