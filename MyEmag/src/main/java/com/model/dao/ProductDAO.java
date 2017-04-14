package com.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.model.Product;
import com.model.Review;
import com.model.User;

public class ProductDAO {
	
	private static ProductDAO instance;
	private static final HashMap<Integer, Product> allproducts = new HashMap<>();//produuctId - > product
	
	private ProductDAO(){
		try {
			getAallproducts();
		} catch (SQLException e) {
			System.out.println("productdao"+e.getMessage());
		}		
	}
	
	public static synchronized ProductDAO getInstance(){
		if(instance == null){
			instance = new ProductDAO();
		}
		return instance;
	}

	public synchronized void addProduct (Product p) throws SQLException{
		int subcategoryId=SubcategoryDAO.getInstance().getSubcategoryId(p.getSubcategory());
		String sql = "INSERT INTO advertisements (title, quantity, price, descr_key1, descr_value1, descr_key2, descr_value2, descr_key3, descr_value3, subcategorie_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		st.setString(1, p.getTitle());
		st.setInt(2, p.getQuantity());
		st.setDouble(3, p.getPrice());
		st.setString(4, p.getDescrKey1());
		st.setString(5, p.getDescrValue1());
		st.setString(6, p.getDescrKey2());
		st.setString(7, p.getDescrValue2());
		st.setString(8, p.getDescrKey3());
		st.setString(9, p.getDescrValue3());
		st.setInt(10, subcategoryId);
		st.executeUpdate();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		int productId = res.getInt(1);
		p.setProductId(productId);
		List<String> imagePaths=p.getImagePaths();
		for (String path : imagePaths) {
			ImageDAO.getInstance().addImagePath(path, productId);
		}
		p.setImagePaths(imagePaths);
		
	}
	
	private HashMap<Integer, Product> getAallproducts() throws SQLException{
		if(allproducts.isEmpty()){
			String sql = "SELECT p.product_id, p.title, quantity, p.price, p.descr_key1, p.descr_value1, p.descr_key2, p.descr_value2, p.descr_key3, p.descr_value3, s.name as subcategory, c.name AS category"
					+ "FROM products p JOIN subcategories s ON (p.subcategory_id=s.subcategory_id), JOIN categories c (s.category_id=c.category_id);";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				int productId=res.getInt("product_id");
				Product p=new Product(res.getString("category"), res.getString("subcategory"), res.getString("title"),
						res.getInt("quantity"), res.getDouble("price"), null, res.getString("descrKey1"), res.getString("descrValue1"),
						res.getString("descrKey2"), res.getString("descrValue1"), res.getString("descrKey3"), res.getString("descrValue3"), null);
				p.setProductId(productId);
				ArrayList<String> imagePaths=ImageDAO.getInstance().getAllImagePathsByProduct(productId);
				p.setImagePaths(imagePaths);
				ArrayList<Review> reviews=ReviewDAO.getInstance().getAllReviewsByProduct(productId);
				for (Review r : reviews) {
					r.setProduct(p);
				}
				p.setReviews(reviews);
				allproducts.put(productId, p);
			}
		}
		return allproducts;
	}
	
	public Product getProduct (int productId) {
		if (allproducts.containsKey(productId)) {
			Product p=allproducts.get(productId);
			return p;			
		}
		return null;
	}
	
	public synchronized ArrayList<Product> getAllProductsFromOrder (int orderId){
		ArrayList<Product> products=new ArrayList<>();
		String sql="SELECT p.product_id, p.title, p.quantity, p.price, p.descr_key1, p.descr_value1, p.descr_key2, p.descr_value2, p.descr_key3, p.descr_value3, s.name as subcategory, c.name AS category "
				+ "FROM products p JOIN orders_has_products o ON (p.product_id=o.product_id)," 
				+ "JOIN subcategories s ON (p.subcategory_id=s.subcategory_id), JOIN categories c (s.category_id=c.category_id)" 
				+ " WHERE order_id=?";
		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(sql);
			st.setInt(1, orderId);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				int productId=res.getInt("product_id");
				Product p=new Product(res.getString("category"), res.getString("subcategory"), res.getString("title"),
						res.getInt("quantity"), res.getDouble("price"), null, res.getString("descrKey1"), res.getString("descrValue1"),
						res.getString("descrKey2"), res.getString("descrValue1"), res.getString("descrKey3"), res.getString("descrValue3"), null);
				p.setProductId(productId);
				ArrayList<String> imagePaths=ImageDAO.getInstance().getAllImagePathsByProduct(productId);
				p.setImagePaths(imagePaths);
				ArrayList<Review> reviews=ReviewDAO.getInstance().getAllReviewsByProduct(productId);
				for (Review r : reviews) {
					r.setProduct(p);
				}
				p.setReviews(reviews);
				products.add(p);
			}
		} catch (SQLException e) {
			System.out.println("SQL : " + e.getMessage());
		}
		return products;
	}
	
}
