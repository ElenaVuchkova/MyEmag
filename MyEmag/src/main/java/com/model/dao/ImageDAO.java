package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImageDAO {

	private static ImageDAO instance;
	
	private ImageDAO(){
	}
	
	public static synchronized ImageDAO getInstance(){
		if(instance == null){
			instance = new ImageDAO();
		}
		return instance;
	}
	
	public synchronized void addImagePath (String path, int productId) throws SQLException{
		String sql = "INSERT INTO categories (path, product_id) values (?,?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, path);	
		st.setInt(2, productId);
		st.executeUpdate();
	}
	
	public synchronized ArrayList<String> getAllImagePathsByProduct (int productId) {
		ArrayList<String> imagePaths=new ArrayList<>();
		String sql="SELECT path FROM images JOIN products USING (product_id) WHERE product_id=?";
		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setInt(1, productId);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				String imagePath=res.getString("path");
				imagePaths.add(imagePath);
			}
		} catch (SQLException e) {
			System.out.println("SQL : " + e.getMessage());
		}
		return imagePaths;
	}
}
