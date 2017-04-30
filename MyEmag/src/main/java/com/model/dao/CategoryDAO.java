package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;


public class CategoryDAO {

	private static CategoryDAO instance;
	private static final  ArrayList<String> ALL_CATEGORIES=new ArrayList<>();
	
	private CategoryDAO() throws SQLException{
		getAllCategories();
	}
	
	public static synchronized CategoryDAO getInstance() throws SQLException{
		if(instance == null){
			instance = new CategoryDAO();
		}
		return instance;
	}
	
	public ArrayList<String> getAllCategories() throws SQLException{
		if(ALL_CATEGORIES.isEmpty()){
			String sql = "SELECT name FROM categories;";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				String category=res.getString("name");
				ALL_CATEGORIES.add(category);
			}
		}
		return ALL_CATEGORIES;
	}

	public synchronized void addCategory (String name, ArrayList<String> subcategories) throws SQLException{
		String sql = "INSERT INTO categories (name) values (?)";
		Connection con=DBManager.getInstance().getConnection();
		PreparedStatement st=null;
		try {
			con.setAutoCommit(false);
			st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, name);	
			st.executeUpdate();
			ResultSet res = st.getGeneratedKeys();
			res.next();
			int categoryId=res.getInt(1);
			for (String subcategory : subcategories) {
				SubcategoryDAO.getInstance().addSubcategory(categoryId, subcategory);
			}
			con.commit();
			ALL_CATEGORIES.add(name);
		} catch (SQLException e){
			System.out.println("SQL transaction to insert category -" + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("SQL transaction to insert category- rollback -" + e.getMessage());
			}
		} finally {
		con.setAutoCommit(true);
		}
	}
	
	public synchronized int getCategoryId (String name) throws SQLException {
		String sql = "SELECT category_id FROM categories WHERE name=?";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs=st.executeQuery();
		rs.next();
		int categoryId=rs.getInt(1);
		return categoryId;
	}
}
