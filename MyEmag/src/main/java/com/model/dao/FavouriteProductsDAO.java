package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.model.Order;
import com.model.Product;
import com.model.User;

public class FavouriteProductsDAO {
	
	private static FavouriteProductsDAO instance;
	
	private FavouriteProductsDAO(){
	}
	
	public static synchronized FavouriteProductsDAO getInstance(){
		if(instance == null){
			instance = new FavouriteProductsDAO();
		}
		return instance;
	}
	
	public synchronized void addFavouriteProduct (int userId, int productId) throws SQLException{
		String sql = "INSERT INTO favouriteproducts (user_id, product_id) values (?,?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setInt(1, userId);
		st.setInt(2, productId);
		st.executeUpdate();
	}

	public synchronized ArrayList<Product> getAllFavouriteProductsByUser (String username) throws SQLException {
		ArrayList<Product> products=new ArrayList<>(); 
		User user=UserDAO.getInstance().getUser(username);
		int userId=user.getUserId();
		String sql="SELECT product_id FROM favouriteproducts WHERE user_id=?";
		PreparedStatement st;
		st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setInt(1, userId);
		ResultSet res = st.executeQuery();
		while (res.next()) {
			int productId=res.getInt("product_id");
			Product p=ProductDAO.getInstance().getProduct(productId);
			products.add(p);
		}
		return products;
		
	}
}
